package io.github.jxch.capitals.chart.controller;

import io.github.jxch.capitals.chart.model.KlineChartParam;
import io.github.jxch.capitals.chart.service.KlineChartService;
import io.github.jxch.capitals.crawler.common.util.WebfluxChartUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.buffer.DefaultDataBuffer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
@RequestMapping("/chart/kline")
public class KlineChartController {
    private final KlineChartService klineChartService;

    @RequestMapping("/kline")
    public Mono<ResponseEntity<DefaultDataBuffer>> imageRoute(@RequestBody KlineChartParam param) {
        return WebfluxChartUtil.image2Mono(() -> klineChartService.klineChart(param));
    }

}
