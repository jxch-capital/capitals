package io.github.jxch.capitals.cloud.gateway.config;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import io.github.jxch.capitals.oauth2.model.JwtAuthUser;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.server.WebFilter;

import java.util.List;

@Data
@RefreshScope
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Value("${spring.security.permits:}")
    private List<String> permits;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, CorsConfigurationSource configurationSource) {
        http
                .cors(corsSpec -> corsSpec.configurationSource(configurationSource))
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(authorize -> {
                    permits.forEach(permit -> authorize.pathMatchers(permit).permitAll());
                    authorize.anyExchange().authenticated();
                })
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .addFilterAt(userSessionWebFilter(), SecurityWebFiltersOrder.LAST)
        ;
        return http.build();
    }

    // todo 把session id放入请求头，保证首次调用时的session共享
    public WebFilter userSessionWebFilter() {
        return (exchange, chain) -> exchange.getPrincipal()
                .flatMap(principal -> exchange.getSession().doOnNext(
                        webSession -> webSession.getAttributes().put(JwtAuthUser.class.getSimpleName(), JSON.toJSONString(BeanUtil.toBean(
                                ((Jwt) ((JwtAuthenticationToken) principal).getCredentials()).getClaims(), JwtAuthUser.class)))))
                .then(chain.filter(exchange));
    }

}