package io.github.jxch.capitals.stock4j.webapp.controller;

import io.github.jxch.capitals.stock4j.api.StockBatchParam;
import io.github.jxch.capitals.stock4j.api.StockBatchRes;
import io.github.jxch.capitals.stock4j.api.StockParam;
import io.github.jxch.capitals.stock4j.api.StockRes;
import io.github.jxch.capitals.stock4j.api3.lb.StockLBApi;
import io.github.jxch.capitals.stock4j.webapp.api.Stock4jApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Stock4jController implements Stock4jApi {
    private final StockLBApi stockLBApi;

    @Override
    public StockRes query(StockParam param) {
        return stockLBApi.query(param);
    }

    @Override
    public StockBatchRes query(StockBatchParam param) {
        return stockLBApi.query(param);
    }

}
