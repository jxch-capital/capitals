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
public class BreathParam {
    @Builder.Default
    private int length = 300;
    @Builder.Default
    private boolean showDate = false;
}
