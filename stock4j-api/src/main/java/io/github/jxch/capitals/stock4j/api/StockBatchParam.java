package io.github.jxch.capitals.stock4j.api;

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
    private Date start;
    @Builder.Default
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date end = Calendar.getInstance().getTime();
    @Builder.Default
    private StockInterval interval = StockInterval.DAY_1;
    @Builder.Default
    private StockEX stockEX = StockEX.GLOBAL;
    @Builder.Default
    private StockEngine stockEngine = StockEngine.DEFAULT;
}
