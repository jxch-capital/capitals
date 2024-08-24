package io.github.jxch.capitals.stock4j.api;

import java.util.List;

public class LBApiSupport implements StockApi {
    private final List<? extends StockApi> apis;

    public LBApiSupport(List<? extends StockApi> apis) {
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

    @Override
    public boolean support(StockParam param) {
        return apis.stream().anyMatch(stockApi -> stockApi.support(param));
    }

    @Override
    public boolean support(StockBatchParam param) {
        return apis.stream().anyMatch(stockApi -> stockApi.support(param));
    }

}
