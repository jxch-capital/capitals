package io.github.jxch.capitals.stock4j.webapp.controller;

import io.github.jxch.capitals.stock4j.api.StockBatchParam;
import io.github.jxch.capitals.stock4j.api.StockBatchRes;
import io.github.jxch.capitals.stock4j.api.StockParam;
import io.github.jxch.capitals.stock4j.api.StockRes;
import io.github.jxch.capitals.stock4j.api3.lb.StockLBApi;
import io.github.jxch.capitals.stock4j.webapp.api.Stock4jApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Stock4jController implements Stock4jApi {
    @Autowired
    private StockLBApi stockLBApi;

    @Override
    public StockRes query(StockParam param) {
        return stockLBApi.query(param);
    }

    @Override
    public StockBatchRes query(StockBatchParam param) {
        return stockLBApi.query(param);
    }

}
