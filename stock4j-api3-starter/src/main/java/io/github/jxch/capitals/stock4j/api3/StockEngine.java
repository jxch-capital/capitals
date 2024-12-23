package io.github.jxch.capitals.stock4j.api3;

import io.github.jxch.capitals.stock4j.api3.yahoo.YahooConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StockEngine {
    YAHOO_API(YahooConfig.YAHOO_API)
    ;
    private final String engine;
}
