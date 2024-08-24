package io.github.jxch.capitals.stock4j.api3.lb;

import io.github.jxch.capitals.stock4j.api.StockApi;
import io.github.jxch.capitals.stock4j.api3.Stock4jApi3Config;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Primary
@Component
public class StockLBApi extends LBApiSupport implements StockApi {

    public StockLBApi(@Qualifier(Stock4jApi3Config.STOCK_API) List<? extends StockApi> apis) {
        super(apis);
    }

}
