package io.github.jxch.index3.dataviz;

import com.alibaba.fastjson2.JSON;
import io.github.jxch.index3.config.MarketIndexAutoConfig;
import io.github.jxch.index3.dataviz.model.DatavizGraphDataRes;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = MarketIndexAutoConfig.class)
class DatavizGraphDataApiTest {
    @Autowired
    private DatavizGraphDataApi datavizGraphDataApi;

    @Test
    void index() {
        DatavizGraphDataRes res = datavizGraphDataApi.graphData();
        log.info("{}", JSON.toJSONString(res));
    }

}