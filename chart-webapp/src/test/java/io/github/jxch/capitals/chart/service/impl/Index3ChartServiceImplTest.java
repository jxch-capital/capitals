package io.github.jxch.capitals.chart.service.impl;

import io.github.jxch.capitals.chart.ChartWebApp;
import io.github.jxch.capitals.chart.model.BreathParam;
import io.github.jxch.capitals.chart.model.FearGreedParam;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Slf4j
@Profile("dev")
@SpringBootTest(classes = {ChartWebApp.class})
class Index3ChartServiceImplTest {
    @Autowired
    private Index3ChartServiceImpl index3ChartService;

    @Test
    void breath() throws IOException {
        BufferedImage image = index3ChartService.breath(BreathParam.builder().length(200).build());

        File file = new File("D:\\jxch-capital\\capitals\\tmp\\test.png");
        ImageIO.write(image, "PNG", file);
    }

    @Test
    void fearGreed() throws IOException {
        BufferedImage image = index3ChartService.fearGreed(FearGreedParam.builder().build());

        File file = new File("D:\\jxch-capital\\capitals\\tmp\\test.png");
        ImageIO.write(image, "PNG", file);
    }
}