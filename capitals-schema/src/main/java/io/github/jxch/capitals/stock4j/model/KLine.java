package io.github.jxch.capitals.stock4j.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "K线数据")
public class KLine {
    @Schema(description = "时间")
    private Date date;
    @Schema(description = "开盘价")
    private Double open;
    @Schema(description = "最高价")
    private Double high;
    @Schema(description = "最低价")
    private Double low;
    @Schema(description = "收盘价")
    private Double close;
    @Schema(description = "成交量")
    private Double volume;
}