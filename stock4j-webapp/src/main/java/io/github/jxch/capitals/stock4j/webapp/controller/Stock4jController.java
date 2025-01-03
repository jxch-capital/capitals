package io.github.jxch.capitals.stock4j.webapp.controller;

import io.github.jxch.capitals.stock4j.api.*;
import io.github.jxch.capitals.stock4j.api3.lb.StockLBApi;
import io.github.jxch.capitals.stock4j.webapp.api.Stock4jApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Stock4jController implements Stock4jApi {
    private final StockLBApi stockLBApi;

    @Override
    public Mono<StockRes> query(StockParam param) {
        return Mono.just(stockLBApi.query(param));
    }

    @Override
    public Mono<StockBatchRes> query(StockBatchParam param) {
        return Mono.just(stockLBApi.query(param));
    }

    @Override
    public Mono<List<String>> stockEngines() {
        return Mono.just(StockEngine.getEngines());
    }

}
