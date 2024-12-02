package io.github.jxch.capitals.index3.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@Data
@AutoConfiguration
@ComponentScan("io.github.jxch.capitals.index3")
public class MarketIndexAutoConfig {
    public final static String MARKET_INDEX_API = "MARKET_INDEX_API";

}
