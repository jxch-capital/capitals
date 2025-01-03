package io.github.jxch.capitals.index3.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "市场呼吸图，单元对象")
public class BreadthCell {
    @Schema(description = "日期")
    private LocalDate date;
    @Schema(description = "板块类型")
    private String type;
    @Schema(description = "板块得分")
    private Integer score;
}
