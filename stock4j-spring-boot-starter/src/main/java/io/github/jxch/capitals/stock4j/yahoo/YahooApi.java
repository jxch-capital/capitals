package io.github.jxch.capitals.stock4j.yahoo;

import io.github.jxch.capitals.stock4j.api.StockParam;
import io.github.jxch.capitals.stock4j.api.StockRes;
import io.github.jxch.capitals.stock4j.config.Stock4jConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier(Stock4jConfig.STOCK_API)
public class YahooApi implements YahooStockApi {
    private final List<YahooStockApi> apis;

    public YahooApi(@Qualifier(YahooConfig.YAHOO_API) List<YahooStockApi> apis) {
        this.apis = apis;
    }

    @Override
    public StockRes query(StockParam param) {
        return apis.stream().filter(stockApi -> stockApi.support(param))
                .findAny().orElseThrow(() -> new UnsupportedOperationException("没有活动的API")).query(param);
    }

}
