package io.github.jxch.capitals.cloud.common;

import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class MonoUtil {

    public static <R> Mono<R> session(Function<WebSession, ? extends Mono<? extends R>> transformer) {
        return Mono.deferContextual(contextView -> contextView.get(ServerWebExchange.class)
                .getSession().flatMap(transformer::apply));
    }

    public static <R> Mono<R> sessionUserid(Function<String, ? extends Mono<? extends R>> transformer) {
        return session(webSession -> transformer.apply(JwtAuthUserUtil.getUserid(webSession)));
    }

}
