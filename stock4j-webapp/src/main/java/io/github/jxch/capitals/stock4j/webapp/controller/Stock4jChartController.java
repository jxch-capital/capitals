package io.github.jxch.capitals.stock4j.webapp.controller;

import io.github.jxch.capitals.stock4j.webapp.dto.StockChartParam;
import io.github.jxch.capitals.stock4j.webapp.service.Stock4jChartService;
import io.github.jxch.capitals.stock4j.webapp.util.WebfluxChartUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.buffer.DefaultDataBuffer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/stock4j/chart/")
@RequiredArgsConstructor
public class Stock4jChartController {
    private final Stock4jChartService stock4jChartService;

    @RequestMapping("/kline")
    public Mono<ResponseEntity<DefaultDataBuffer>> imageRoute(@RequestBody StockChartParam param) {
        return WebfluxChartUtil.image2Mono(() -> stock4jChartService.klineChart(param));
    }

}
