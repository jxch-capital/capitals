package io.github.jxch.capitals.cloud.gateway.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

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
    @Value("${gateway.login.default-redirect-url:/capitals/}")
    private String defaultLoginRedirectUrl;
    @Value("${gateway.login.redirect-url-param:redirect-to}")
    private String redirectUrlParam;
    @Value("${gateway.login.allow-redirect-host:localhost,127.0.0.1}")
    private List<String> allowRedirectHost;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, GatewayServerOAuth2Processor requestResolver) {
        http
                .cors(corsSpec -> corsSpec.configurationSource(configurationSource()))
                .csrf(csrfSpec -> csrfSpec.requireCsrfProtectionMatcher((exchange) -> ServerWebExchangeMatcher.MatchResult.notMatch()))
                .authorizeExchange(authorize -> {
                    permits.forEach(permit -> authorize.pathMatchers(permit).permitAll());
                    authorize.anyExchange().authenticated();
                })
                .oauth2Login(oauth2LoginSpec -> oauth2LoginSpec.authorizationRequestResolver(requestResolver)
                        .authenticationSuccessHandler(requestResolver));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
        configuration.addAllowedHeader(CorsConfiguration.ALL);
        configuration.addAllowedMethod(CorsConfiguration.ALL);
        configuration.setAllowCredentials(true);
        configuration.setExposedHeaders(Collections.singletonList(HttpHeaders.SET_COOKIE));

        org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}