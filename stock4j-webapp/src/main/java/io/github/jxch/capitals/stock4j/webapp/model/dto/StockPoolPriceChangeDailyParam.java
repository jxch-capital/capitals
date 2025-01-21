package io.github.jxch.capitals.stock4j.webapp.model.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "股票池日线价格变动查询参数")
public class StockPoolPriceChangeDailyParam {
    @NotNull(message = "股票池ID列表不能为空")
    @Schema(description = "股票池ID列表")
    private List<Long> stockPoolIds;
    @NotNull(message = "日线间隔天数不能为空")
    @Schema(description = "日线间隔天数", example = "[20,60,120]")
    private List<Integer> dailyIntervals;

    @Hidden
    public Integer getMaxDailyInterval() {
        return dailyIntervals.stream().max(Integer::compareTo).orElse(0);
    }
}
