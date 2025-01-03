package io.github.jxch.capitals.stock4j.model;

import io.github.jxch.capitals.stock4j.type.StockEX;
import io.github.jxch.capitals.stock4j.type.StockEngine;
import io.github.jxch.capitals.stock4j.type.StockInterval;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "股票批量查询接口参数")
public class StockBatchParam {
    @Schema(description = "股票代码列表", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> codes;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @Schema(description = "开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private Date start;
    @Builder.Default
    @Schema(description = "结束时间，默认当前时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date end = Calendar.getInstance().getTime();
    @Builder.Default
    @Schema(description = "周期，默认日线")
    private StockInterval interval = StockInterval.DAY_1;
    @Builder.Default
    @Schema(description = "交易所，默认全球")
    private StockEX stockEX = StockEX.GLOBAL;
    @Builder.Default
    @Schema(description = "股票引擎，默认全部")
    private StockEngine stockEngine = StockEngine.DEFAULT;
}
