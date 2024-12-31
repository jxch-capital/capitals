package io.github.jxch.capitals.cloud.gateway.config;

import io.github.jxch.capitals.cloud.common.model.UserCookie;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;
import java.util.List;

@Data
@RefreshScope
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Value("${spring.security.permits:}")
    private List<String> permits;
    @Value("${gateway.cookie.max-age:1}")
    private Integer cookieMaxAge;
    @Value("${gateway.cookie.path:/}")
    private String cookiePath;
    @Value("${gateway.login.default-redirect-url:/capitals/}")
    private String defaultLoginRedirectUrl;
    @Value("${gateway.login.redirect-url-param:state}")
    private String redirectUrlParam;
    @Value("${gateway.login.allow-redirect-host:localhost,127.0.0.1}")
    private List<String> allowRedirectHost;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(csrfSpec -> csrfSpec.requireCsrfProtectionMatcher((exchange) -> ServerWebExchangeMatcher.MatchResult.notMatch()))
                .authorizeExchange(authorize -> {
                    permits.forEach(permit -> authorize.pathMatchers(permit).permitAll());
                    authorize.anyExchange().authenticated();
                })
                .oauth2Login(oauth2LoginSpec -> oauth2LoginSpec
                        .authenticationSuccessHandler(authenticationSuccessHandler()));

        return http.build();
    }

    @Bean
    public ServerAuthenticationSuccessHandler authenticationSuccessHandler() {
        return (webFilterExchange, authentication) -> {
            UserCookie userCookie = UserCookie.parseObject(authentication.getPrincipal());
            webFilterExchange.getExchange().getResponse().addCookie(ResponseCookie.from(UserCookie.class.getSimpleName(), userCookie.toUrlJson())
                    .httpOnly(true).secure(true).path(cookiePath).maxAge(Duration.ofHours(cookieMaxAge)).build());
            return Mono.fromRunnable(() -> {
                ServerWebExchange exchange = webFilterExchange.getExchange();

                String state = exchange.getRequest().getQueryParams().getFirst(redirectUrlParam);
                String redirectUrl = (state != null && isValidRedirectUri(state)) ? state :
                        UriComponentsBuilder.fromPath(defaultLoginRedirectUrl).build().toUriString();

                exchange.getResponse().setStatusCode(HttpStatus.FOUND);  // 302 重定向
                exchange.getResponse().getHeaders().setLocation(URI.create(redirectUrl));
            });
        };
    }

    private boolean isValidRedirectUri(String uri) {
        try {
            URI parsedUri = new URI(uri);
            String host = parsedUri.getHost();
            return host != null && allowRedirectHost.stream().anyMatch(host::endsWith);
        } catch (Exception e) {
            return false;
        }
    }

}