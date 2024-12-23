package io.github.jxch.capitals.news.brooks.api;

import io.github.jxch.capitals.crawler.common.CrawlerAutoConfig;
import io.github.jxch.capitals.news.CapitalsNewsAutoConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URL;

@Slf4j
@SpringBootTest(classes = {CapitalsNewsAutoConfig.class, CrawlerAutoConfig.class})
class BrooksBlogApiTest {
    @Autowired
    private BrooksBlogApi brooksBlogApi;

    @Test
    void newArticleFirstKChartUrl() {
        URL url = brooksBlogApi.newArticleFirstKChartUrl();
        log.info(url.toString());
    }

}