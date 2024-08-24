package io.github.jxch.capitals.stock4j.api;

import io.github.jxch.capitals.stock4j.config.Stock4jConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Primary
@Component
public class StockApiLoadBalance implements StockApi {
    private final List<StockApi> apis;

    public StockApiLoadBalance(@Qualifier(Stock4jConfig.STOCK_API) List<StockApi> apis) {
        this.apis = apis;
    }

    @Override
    public StockRes query(StockParam param) {
        return apis.stream().filter(stockApi -> stockApi.support(param))
                .findAny().orElseThrow(() -> new UnsupportedOperationException("没有活动的API")).query(param);
    }

    @Override
    public StockBatchRes query(StockBatchParam param) {
        return apis.stream().filter(stockApi -> stockApi.support(param))
                .findAny().orElseThrow(() -> new UnsupportedOperationException("没有活动的API")).query(param);
    }

}
