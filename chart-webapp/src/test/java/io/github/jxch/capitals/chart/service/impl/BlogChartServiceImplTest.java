package io.github.jxch.capitals.chart.service.impl;

import io.github.jxch.capitals.chart.ChartWebApp;
import io.github.jxch.capitals.chart.service.BlogChartService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.awt.image.BufferedImage;

@Slf4j
@Profile("dev")
@SpringBootTest(classes = {ChartWebApp.class})
class BlogChartServiceImplTest {
    @Autowired
    private BlogChartService blogChartService;

    @Test
    void brooksFirstArticleChart() {
        BufferedImage bufferedImage = blogChartService.brooksFirstArticleChart();
    }

}