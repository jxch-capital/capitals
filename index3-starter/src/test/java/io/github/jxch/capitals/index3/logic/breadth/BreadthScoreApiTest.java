package io.github.jxch.capitals.index3.logic.breadth;

import com.alibaba.fastjson2.JSON;
import io.github.jxch.capitals.crawler.common.CrawlerAutoConfig;
import io.github.jxch.capitals.index3.MarketIndexAutoConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = {MarketIndexAutoConfig.class, CrawlerAutoConfig.class})
class BreadthScoreApiTest {
    @Autowired
    private BreadthScoreApi breadthScoreApi;

    @Test
    void index() {
        var res = breadthScoreApi.breadthScore();
        log.info("{}", JSON.toJSONString(res.getItemList()));
    }
}