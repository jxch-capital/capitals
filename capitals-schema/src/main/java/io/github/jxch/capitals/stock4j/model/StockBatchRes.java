package io.github.jxch.capitals.stock4j.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "股票批量查询接口响应结果")
public class StockBatchRes {
    @Schema(description = "key: 股票代码；value: K线图")
    private Map<String, List<KLine>> kLines = new HashMap<>();

    public StockBatchRes addByStockRes(StockRes stockRes) {
        kLines.put(stockRes.getCode(), stockRes.getKLines());
        return this;
    }

    public StockBatchRes addAllByStockRes(List<StockRes> stockRes) {
        stockRes.forEach(this::addByStockRes);
        return this;
    }

}