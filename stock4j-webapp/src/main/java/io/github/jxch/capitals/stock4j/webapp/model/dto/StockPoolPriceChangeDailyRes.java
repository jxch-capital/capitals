package io.github.jxch.capitals.stock4j.webapp.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "股票池日线价格变动查询响应结果")
public class StockPoolPriceChangeDailyRes {
    @Schema(description = "所有股票的日线价格变动列表，按股票池分类")
    private Map<String, List<StockPoolPriceChangeDaily>> priceChange;

    public static StockPoolPriceChangeDailyRes priceChangeByList(List<StockPoolPriceChangeDaily> priceChangeDailies) {
        return StockPoolPriceChangeDailyRes.builder()
                .priceChange(priceChangeDailies.parallelStream().collect(Collectors.groupingBy(StockPoolPriceChangeDaily::getStockPoolName)))
                .build();
    }

}
