package io.github.jxch.capitals.index3;

import lombok.Data;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Data
@ComponentScan
@Configuration
public class MarketIndexAutoConfig {
    public final static String MARKET_INDEX_API = "MARKET_INDEX_API";

}
