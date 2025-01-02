package io.github.jxch.capitals.cloud.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/public")
public class PublicController {

    @RequestMapping("/session")
    public Mono<Map<String, Object>> session(WebSession session) {
        session.getAttributes().put("session", session.getId());
        return Mono.just(session.getAttributes());
    }

}
