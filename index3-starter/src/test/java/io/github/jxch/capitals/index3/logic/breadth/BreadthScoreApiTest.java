package io.github.jxch.capitals.index3.logic.breadth;

import com.alibaba.fastjson2.JSON;
import io.github.jxch.capitals.crawler.common.config.CrawlerAutoConfig;
import io.github.jxch.capitals.index3.config.MarketIndexAutoConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

@Slf4j

@Profile("application-test.yml")
@SpringBootTest(classes = {MarketIndexAutoConfig.class, CrawlerAutoConfig.class})
@ComponentScan("io.github.jxch.capitals.crawler.common")
class BreadthScoreApiTest {
    @Autowired
    private BreadthScoreApi breadthScoreApi;

    @Test
    void index() {
        var res = breadthScoreApi.breadthScore();
        log.info("{}", JSON.toJSONString(res.getItemList()));
    }
}