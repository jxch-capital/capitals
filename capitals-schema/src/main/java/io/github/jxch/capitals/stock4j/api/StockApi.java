package io.github.jxch.capitals.stock4j.api;

import io.github.jxch.capitals.stock4j.model.StockBatchParam;
import io.github.jxch.capitals.stock4j.model.StockBatchRes;
import io.github.jxch.capitals.stock4j.model.StockParam;
import io.github.jxch.capitals.stock4j.model.StockRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Objects;

@Tag(name = "股票查询接口", description = "股票查询通用接口")
public interface StockApi {

    @Operation(summary = "单只股票历史K线查询", description = "查询单只股票的历史价格K线")
    StockRes query(StockParam param);

    @Operation(summary = "批量股票历史K线查询", description = "查询批量股票的历史价格K线")
    default StockBatchRes query(StockBatchParam param) {
        List<StockParam> params = param.getCodes().stream().map(code -> StockParam.builder()
                .code(code)
                .start(param.getStart())
                .end(param.getEnd())
                .interval(param.getInterval())
                .build()).toList();

        List<StockRes> stockRes = params.stream().map(item -> {
            try {
                return query(item);
            } catch (Exception e) {
                return null;
            }
        }).filter(Objects::nonNull).toList();

        StockBatchRes stockBatchRes = new StockBatchRes();
        stockBatchRes.addAllByStockRes(stockRes);
        return stockBatchRes;
    }

    @Operation(summary = "是否支持单只股票历史K线查询")
    default boolean support(StockParam param) {
        return true;
    }

    @Operation(summary = "是否支持批量股票历史K线查询")
    default boolean support(StockBatchParam param) {
        return false;
    }

}
