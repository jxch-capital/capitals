package io.github.jxch.capitals.stock4j.api;

import java.util.List;

public interface StockApi {

    StockRes query(StockParam param);

    default StockBatchRes query(StockBatchParam param) {
        List<StockParam> params = param.getCodes().stream().map(code -> StockParam.builder()
                .code(code)
                .start(param.getStart())
                .end(param.getEnd())
                .interval(param.getInterval())
                .build()).toList();

        List<StockRes> stockRes = params.stream().map(this::query).toList();

        StockBatchRes stockBatchRes = new StockBatchRes();
        stockBatchRes.addAllByStockRes(stockRes);
        return stockBatchRes;
    }

    default boolean support(StockParam param) {
        return true;
    }

    default boolean support(StockBatchParam param) {
        return false;
    }

}
