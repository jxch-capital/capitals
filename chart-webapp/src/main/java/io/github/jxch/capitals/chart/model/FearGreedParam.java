package io.github.jxch.capitals.chart.model;

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
public class FearGreedParam {
    @Builder.Default
    private Integer width = 800;
    @Builder.Default
    private Integer height = 400;
}
