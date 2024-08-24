package io.github.jxch.capitals.stock4j;

import com.alibaba.fastjson2.JSON;
import io.github.jxch.capitals.stock4j.api.*;
import io.github.jxch.capitals.stock4j.config.Stock4jConfig;
import io.github.jxch.capitals.stock4j.yahoo.YahooLBApi;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Slf4j
@SpringBootTest(classes = Stock4jConfig.class)
public class StockApiTest {

    @Autowired
    private YahooLBApi yahooLBApi;

    @Autowired
    private StockLBApi stockLBApi;

    @Test
    void yahooQuery() {
        StockRes stockRes = yahooLBApi.query(StockParam.builder().code("QQQ")
                .start(Date.from(LocalDate.now().plusDays(-20).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                .build());
        log.info(JSON.toJSONString(stockRes));
    }

    @Test
    void yahooQueryBatch() {
        StockBatchRes stockRes = yahooLBApi.query(StockBatchParam.builder().codes(List.of("QQQ", "SPY"))
                .start(Date.from(LocalDate.now().plusDays(-20).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                .interval(StockInterval.REAL)
                .build());
        log.info(JSON.toJSONString(stockRes));
    }

    @Test
    void stockQuery() {
        StockRes stockRes = stockLBApi.query(StockParam.builder().code("QQQ")
                .start(Date.from(LocalDate.now().plusDays(-20).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                .build());
        log.info(JSON.toJSONString(stockRes));
    }

    @Test
    void stockQueryBatch() {
        StockBatchRes stockRes = stockLBApi.query(StockBatchParam.builder().codes(List.of("QQQ", "SPY"))
                .start(Date.from(LocalDate.now().plusDays(-20).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                .interval(StockInterval.REAL)
                .build());
        log.info(JSON.toJSONString(stockRes));
    }
    
}
