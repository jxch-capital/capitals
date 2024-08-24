package io.github.jxch.capitals.stock4j.yahoo;

import io.github.jxch.capitals.stock4j.api.LBApiSupport;
import io.github.jxch.capitals.stock4j.api.StockApi;
import io.github.jxch.capitals.stock4j.config.Stock4jConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier(Stock4jConfig.STOCK_API)
public class YahooLBApi extends LBApiSupport implements StockApi {

    public YahooLBApi(@Qualifier(YahooConfig.YAHOO_API) List<? extends StockApi> apis) {
        super(apis);
    }

}
