package io.github.jxch.capitals.stock4j.webapp.dto;

import io.github.jxch.capitals.stock4j.api.StockParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class StockChartParam {
    private StockParam stockParam;

    @Builder.Default
    private Integer length = 90;
    @Builder.Default
    private Integer width = 2560;
    @Builder.Default
    private Integer height = 1440;
}
