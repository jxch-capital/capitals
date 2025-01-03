package io.github.jxch.capitals.stock4j.model;

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
@Schema(description = "单只股票查询接口响应结果")
public class StockRes {
    @Schema(description = "股票代码")
    private String code;
    @Schema(description = "K线数据")
    private List<KLine> kLines;
}
