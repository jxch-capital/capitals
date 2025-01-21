package io.github.jxch.capitals.stock4j.webapp.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "股票池日线价格变动")
public class StockPoolPriceChangeDaily {
    @Schema(description = "股票池名称")
    private String stockPoolName;
    @Schema(description = "股票代码")
    private String stockCode;
    @Schema(description = "日线价格变动")
    private Map<Integer, Double> priceChange;
}
