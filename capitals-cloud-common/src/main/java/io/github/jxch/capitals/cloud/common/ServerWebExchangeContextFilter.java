package io.github.jxch.capitals.cloud.common;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class ServerWebExchangeContextFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return chain.filter(exchange)
                .contextWrite(ctx -> {
                    if (!ctx.hasKey(ServerWebExchange.class)) {
                        return ctx.put(ServerWebExchange.class, exchange);
                    }
                    return ctx;
                });
    }

}
