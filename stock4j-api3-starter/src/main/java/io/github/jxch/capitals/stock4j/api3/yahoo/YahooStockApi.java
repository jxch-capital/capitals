package io.github.jxch.capitals.stock4j.api3.yahoo;

import io.github.jxch.capitals.stock4j.api.StockApi;
import io.github.jxch.capitals.stock4j.model.StockBatchParam;
import io.github.jxch.capitals.stock4j.model.StockParam;
import io.github.jxch.capitals.stock4j.type.StockEngine;

import java.util.Objects;

public interface YahooStockApi extends StockApi {

    @Override
    default boolean support(StockParam param) {
        return Objects.equals(param.getStockEngine(), StockEngine.YAHOO_API) || Objects.equals(param.getStockEngine(), StockEngine.DEFAULT);
    }

    @Override
    default boolean support(StockBatchParam param) {
        return Objects.equals(param.getStockEngine(), StockEngine.YAHOO_API) || Objects.equals(param.getStockEngine(), StockEngine.DEFAULT);
    }

}
