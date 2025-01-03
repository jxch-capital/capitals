package io.github.jxch.capitals.index3.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "市场呼吸图参数")
public class BreathParam {
    @Builder.Default
    @Schema(description = "显示最近几条的数据")
    private int length = 300;
}
