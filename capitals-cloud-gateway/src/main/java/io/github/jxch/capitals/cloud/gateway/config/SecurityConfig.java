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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;
import java.util.Collections;
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
    @Value("${gateway.login.redirect-url:/capitals/}")
    private String loginRedirectUrl;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(csrfSpec -> csrfSpec.requireCsrfProtectionMatcher((exchange) -> ServerWebExchangeMatcher.MatchResult.notMatch()))
                .cors(corsSpec -> corsSpec.configurationSource(configurationSource()))
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
                exchange.getResponse().setStatusCode(HttpStatus.FOUND);  // 302 重定向
                String redirectUrl = UriComponentsBuilder.fromPath(loginRedirectUrl).build().toUriString();
                exchange.getResponse().getHeaders().setLocation(URI.create(redirectUrl));
            });
        };
    }

    public CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
        configuration.addAllowedHeader(CorsConfiguration.ALL);
        configuration.addAllowedMethod(CorsConfiguration.ALL);
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}