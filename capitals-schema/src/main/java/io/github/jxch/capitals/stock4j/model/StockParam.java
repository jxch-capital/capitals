package io.github.jxch.capitals.stock4j.model;

import cn.hutool.core.date.DateUtil;
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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "单只股票查询接口参数")
public class StockParam {
    @Schema(description = "股票代码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String code = "SPY";
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Schema(description = "开始时间，默认1年前")
    private Date start = DateUtil.offsetYear(Calendar.getInstance().getTime(), -1);
    @Builder.Default
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Schema(description = "结束时间，默认现在")
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
