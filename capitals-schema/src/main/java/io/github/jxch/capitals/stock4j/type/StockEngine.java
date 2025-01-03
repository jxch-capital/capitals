package io.github.jxch.capitals.stock4j.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum StockEngine {
    DEFAULT,
    YAHOO_API,
    ;

    public static List<String> getEngines() {
        return Arrays.stream(StockEngine.values()).map(StockEngine::name).toList();
    }

}

