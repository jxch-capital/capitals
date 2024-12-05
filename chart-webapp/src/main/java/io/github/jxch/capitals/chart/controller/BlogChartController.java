package io.github.jxch.capitals.chart.controller;

import io.github.jxch.capitals.chart.service.BlogChartService;
import io.github.jxch.capitals.crawler.common.util.WebfluxChartUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.buffer.DefaultDataBuffer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chart/blog")
public class BlogChartController {
    private final BlogChartService blogChartService;

    @RequestMapping("/brooks")
    public Mono<ResponseEntity<DefaultDataBuffer>> brooks() {
        return WebfluxChartUtil.image2Mono(blogChartService::brooksFirstArticleChart);
    }

}