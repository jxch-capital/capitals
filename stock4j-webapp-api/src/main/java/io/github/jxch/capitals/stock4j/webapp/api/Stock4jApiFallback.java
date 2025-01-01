package io.github.jxch.capitals.stock4j.webapp.api;

import io.github.jxch.capitals.stock4j.api.StockBatchParam;
import io.github.jxch.capitals.stock4j.api.StockBatchRes;
import io.github.jxch.capitals.stock4j.api.StockParam;
import io.github.jxch.capitals.stock4j.api.StockRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class Stock4jApiFallback implements Stock4jApi {

    @Override
    public Mono<StockRes> query(StockParam param) {
        log.warn("Stock4jApiFallback: query");
        return Mono.just(StockRes.builder().build());
    }

    @Override
    public Mono<StockBatchRes> query(StockBatchParam param) {
        log.warn("Stock4jApiFallback: queryBatch");
        return Mono.just(StockBatchRes.builder().build());
    }

}
