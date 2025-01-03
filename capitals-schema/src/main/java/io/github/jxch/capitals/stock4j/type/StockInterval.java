package io.github.jxch.capitals.stock4j.type;

import cn.hutool.core.date.DateField;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StockInterval {
    YEAR_1(DateField.YEAR),
    MONTH_1(DateField.MONTH),
    DAY_1(DateField.DAY_OF_YEAR),
    HOUR_1(DateField.HOUR_OF_DAY),
    HOUR_4(DateField.HOUR_OF_DAY),
    MINUTE_1(DateField.MINUTE),
    MINUTE_5(DateField.MINUTE),
    REAL(DateField.MILLISECOND),
    ;
    private final DateField dateField;
}
