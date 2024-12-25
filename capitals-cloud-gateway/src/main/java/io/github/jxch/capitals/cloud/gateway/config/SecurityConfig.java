package io.github.jxch.capitals.cloud.gateway.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.List;

@Data
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Value("${spring.security.permits:}")
    private List<String> permits;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(authorize -> {
                    permits.forEach(permit -> authorize.pathMatchers(permit).permitAll());
                    authorize.anyExchange().authenticated();
                })
                .oauth2Login(Customizer.withDefaults())
                .csrf(ServerHttpSecurity.CsrfSpec::disable);

        return http.build();
    }

}