package io.github.jxch.capitals.stock4j.api;

import cn.hutool.core.date.DateUtil;
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
public class StockParam {
    private String code = "SPY";
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date start = DateUtil.offsetYear(Calendar.getInstance().getTime(), -1);
    @Builder.Default
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date end = Calendar.getInstance().getTime();
    @Builder.Default
    private StockInterval interval = StockInterval.DAY_1;
    @Builder.Default
    private StockEX stockEX = StockEX.GLOBAL;
}
