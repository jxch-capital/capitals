package io.github.jxch.capitals.stock4j.api;

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
public class StockBatchParam {
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
}
