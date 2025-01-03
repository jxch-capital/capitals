package io.github.jxch.capitals.index3.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "市场呼吸图返回结果")
public class BreadthRes {
    @Schema(description = "单元得分列表")
    private List<BreadthCell> breadthCells;
}
