package io.github.jxch.capitals.cloud.gateway.filter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Order(0)
@Component
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "spring.cloud.gateway.global-filter.response-header-distinct")
public class ResponseHeaderDistinctFilter implements GlobalFilter {
    private List<String> headers;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            HttpHeaders httpHeaders = exchange.getResponse().getHeaders();
            for (String header : this.headers) {
                List<String> values = httpHeaders.get(header);
                if (Objects.nonNull(values) && !values.isEmpty()) {
                    httpHeaders.put(header, values.stream().distinct().collect(Collectors.toList()));
                }
            }
        }));
    }

}
