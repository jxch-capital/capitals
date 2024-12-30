package io.github.jxch.capitals.cloud.gateway.controller;

import io.github.jxch.capitals.cloud.common.model.UserCookie;
import io.github.jxch.capitals.cloud.gateway.config.SecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class GatewayUserController {
    private final SecurityConfig securityConfig;

    @RequestMapping("/login")
    public Mono<Void> login(Authentication authentication, ServerHttpResponse response) {
        String redirectUrl = Objects.isNull(authentication) || !authentication.isAuthenticated() ? "/login" : securityConfig.getLoginRedirectUrl();

        response.setStatusCode(HttpStatus.FOUND);
        response.getHeaders().setLocation(URI.create(redirectUrl));
        return Mono.empty();
    }

    @RequestMapping("/public/userCookie")
    public Mono<UserCookie> getUserCookie(Authentication authentication, ServerHttpRequest request) {
        if (Objects.isNull(authentication) || !authentication.isAuthenticated()) {
            return Mono.empty();
        }

        return Mono.just(UserCookie.parseUrlJson(request.getCookies().get(UserCookie.class.getSimpleName()).stream().findFirst().orElseThrow().getValue()));
    }

}
