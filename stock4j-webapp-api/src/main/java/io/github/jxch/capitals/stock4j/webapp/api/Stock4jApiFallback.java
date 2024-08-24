package io.github.jxch.capitals.stock4j.webapp.api;

import io.github.jxch.capitals.stock4j.api.StockBatchParam;
import io.github.jxch.capitals.stock4j.api.StockBatchRes;
import io.github.jxch.capitals.stock4j.api.StockParam;
import io.github.jxch.capitals.stock4j.api.StockRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Stock4jApiFallback implements Stock4jApi {

    @Override
    public StockRes query(StockParam param) {
        log.warn("Stock4jApiFallback: query");
        return StockRes.builder().build();
    }

    @Override
    public StockBatchRes query(StockBatchParam param) {
        log.warn("Stock4jApiFallback: queryBatch");
        return StockBatchRes.builder().build();
    }

}
