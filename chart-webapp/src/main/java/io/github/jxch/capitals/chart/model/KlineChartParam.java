package io.github.jxch.capitals.chart.model;

import io.github.jxch.capitals.stock4j.model.StockParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class KlineChartParam {

    private StockParam stockParam;

    @Builder.Default
    private Integer length = 90;
    @Builder.Default
    private Integer width = 2560;
    @Builder.Default
    private Integer height = 1440;

}
