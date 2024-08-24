package io.github.jxch.capitals.stock4j.api;

import io.github.jxch.capitals.stock4j.config.Stock4jConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Primary
@Component
public class StockLBApi extends LBApiSupport implements StockApi {

    public StockLBApi(@Qualifier(Stock4jConfig.STOCK_API) List<? extends StockApi> apis) {
        super(apis);
    }

}
