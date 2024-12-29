package io.github.jxch.capitals.cloud.gateway.controller;

import io.github.jxch.capitals.cloud.gateway.config.SecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class GatewayUserController {
    private final SecurityConfig securityConfig;

    @RequestMapping("/login")
    public Mono<Void> login(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.FOUND);
        response.getHeaders().setLocation(URI.create(securityConfig.getLoginRedirectUrl()));
        return Mono.empty();
    }

}
