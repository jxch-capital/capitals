package io.github.jxch.capitals.chart.service.impl;

import io.github.jxch.capitals.chart.service.BlogChartService;
import io.github.jxch.capitals.news.brooks.api.BrooksBlogApi;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BlogChartServiceImpl implements BlogChartService {
    private final BrooksBlogApi brooksBlogApi;

    @Override
    @SneakyThrows
    public BufferedImage brooksFirstArticleChart() {
        URL url = brooksBlogApi.newArticleFirstChartUrl();
        return Objects.nonNull(url) ? ImageIO.read(url)
                : new BufferedImage(0, 0, BufferedImage.TYPE_INT_RGB);
    }

}
