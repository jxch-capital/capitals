package io.github.jxch.capitals.cloud.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/public")
public class PublicController {

    @RequestMapping("session")
    public Mono<String> session(WebSession session) {
        session.getAttributes().put("session", session.getId());
        return Mono.just(session.getId());
    }

}
