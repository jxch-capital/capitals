package io.github.jxch.capitals.chart.controller;

import io.github.jxch.capitals.chart.model.BreathParam;
import io.github.jxch.capitals.chart.model.FearGreedParam;
import io.github.jxch.capitals.chart.service.Index3ChartService;
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
@RequestMapping("/index3")
public class Index3ChartController {
    private final Index3ChartService index3ChartService;

    @RequestMapping("/breath")
    public Mono<ResponseEntity<DefaultDataBuffer>> breath(@RequestBody BreathParam param) {
        return WebfluxChartUtil.image2Mono(() -> index3ChartService.breath(param));
    }

    @RequestMapping("/fearGreed")
    public Mono<ResponseEntity<DefaultDataBuffer>> fearGreed(@RequestBody FearGreedParam param) {
        return WebfluxChartUtil.image2Mono(() -> index3ChartService.fearGreed(param));
    }

}
