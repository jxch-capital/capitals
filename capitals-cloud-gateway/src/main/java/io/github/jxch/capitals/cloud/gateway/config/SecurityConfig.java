package io.github.jxch.capitals.cloud.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .securityMatcher(ServerWebExchangeMatchers.anyExchange()) // 全局匹配请求
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/public/**").permitAll() // 允许匿名访问
                        .pathMatchers("/admin/**").hasRole("ADMIN") // 仅管理员访问
                        .pathMatchers("/users/**").hasRole("USER") // 仅普通用户访问
                        .anyExchange().authenticated() // 其他请求需认证
                )
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // 禁用 CSRF
                .build();
    }


}
