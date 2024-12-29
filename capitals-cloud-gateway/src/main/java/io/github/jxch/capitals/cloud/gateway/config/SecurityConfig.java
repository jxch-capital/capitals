package io.github.jxch.capitals.cloud.gateway.config;

import io.github.jxch.capitals.cloud.common.model.UserCookie;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseCookie;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import reactor.core.publisher.Mono;

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

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(authorize -> {
                    permits.forEach(permit -> authorize.pathMatchers(permit).permitAll());
                    authorize.anyExchange().authenticated();
                })
                .oauth2Login(oauth2LoginSpec -> oauth2LoginSpec.authenticationSuccessHandler(authenticationSuccessHandler()))
                .csrf(ServerHttpSecurity.CsrfSpec::disable);

        return http.build();
    }

    @Bean
    public ServerAuthenticationSuccessHandler authenticationSuccessHandler() {
        return (webFilterExchange, authentication) -> {
            UserCookie userCookie = UserCookie.parseObject(authentication.getPrincipal());
            webFilterExchange.getExchange().getResponse().addCookie(ResponseCookie.from(UserCookie.class.getSimpleName(), userCookie.toUrlJson())
                    .httpOnly(true).secure(true).path(cookiePath).maxAge(Duration.ofHours(cookieMaxAge)).build());
            return Mono.empty();
        };
    }

}