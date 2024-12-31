package io.github.jxch.capitals.cloud.gateway.config;

import io.github.jxch.capitals.cloud.common.model.UserCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.server.DefaultServerOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Objects;

@Component
public class GatewayServerOAuth2Processor implements ServerOAuth2AuthorizationRequestResolver, ServerAuthenticationSuccessHandler {
    private final ServerOAuth2AuthorizationRequestResolver defaultResolver;
    private final SecurityConfig securityConfig;

    public GatewayServerOAuth2Processor(ReactiveClientRegistrationRepository clientRegistrationRepository, SecurityConfig securityConfig) {
        this.defaultResolver = new DefaultServerOAuth2AuthorizationRequestResolver(clientRegistrationRepository);
        this.securityConfig = securityConfig;
    }

    @Override
    public Mono<OAuth2AuthorizationRequest> resolve(ServerWebExchange exchange) {
        return defaultResolver.resolve(exchange).map(authorizationRequest -> resolve(authorizationRequest, exchange));
    }

    @Override
    public Mono<OAuth2AuthorizationRequest> resolve(ServerWebExchange exchange, String clientRegistrationId) {
        return defaultResolver.resolve(exchange, clientRegistrationId).map(authorizationRequest -> resolve(authorizationRequest, exchange));
    }

    private OAuth2AuthorizationRequest resolve(OAuth2AuthorizationRequest authorizationRequest, ServerWebExchange exchange) {
        String redirectTo = exchange.getRequest().getQueryParams().getFirst(securityConfig.getRedirectUrlParam());
        if (StringUtils.hasText(redirectTo)) {
            String encodedRedirectTo = UriUtils.encode(redirectTo, StandardCharsets.UTF_8);
            String originalState = authorizationRequest.getState();
            String newState = originalState + "&" + securityConfig.getRedirectUrlParam() + "=" + encodedRedirectTo;

            return OAuth2AuthorizationRequest.from(authorizationRequest)
                    .state(newState)
                    .build();
        }
        return authorizationRequest;
    }

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        UserCookie userCookie = UserCookie.parseObject(authentication.getPrincipal());
        webFilterExchange.getExchange().getResponse().addCookie(ResponseCookie.from(UserCookie.class.getSimpleName(), userCookie.toUrlJson())
                .httpOnly(true).secure(true).path(securityConfig.getCookiePath()).maxAge(Duration.ofHours(securityConfig.getCookieMaxAge())).build());
        return Mono.fromRunnable(() -> {
            ServerWebExchange exchange = webFilterExchange.getExchange();

            String state = exchange.getRequest().getQueryParams().getFirst("state");
            state = URLDecoder.decode(state, StandardCharsets.UTF_8);
            String redirectUrl = hasValidRedirectUri(state) ? getRedirectUri(state) :
                    UriComponentsBuilder.fromPath(securityConfig.getDefaultLoginRedirectUrl()).build().toUriString();

            exchange.getResponse().setStatusCode(HttpStatus.FOUND);  // 302 重定向
            exchange.getResponse().getHeaders().setLocation(URI.create(redirectUrl));
        });
    }

    // todo 不够健壮（只支持一个参数）
    private boolean hasValidRedirectUri(String state) {
        if (Objects.nonNull(state) && StringUtils.hasText(state) && state.contains("&") && state.contains(securityConfig.getRedirectUrlParam() + "=")) {
            try {
                URI parsedUri = new URI(getRedirectUri(state));
                String host = parsedUri.getHost();
                return host != null && securityConfig.getAllowRedirectHost().stream().anyMatch(host::endsWith);
            } catch (Exception e) {
                return false;
            }
        }

        return false;
    }

    private String getRedirectUri(String state) {
        return state.split("&")[1].split("=")[1];
    }

}
