package io.github.jxch.capitals.stock4j.webapp.service;

import io.github.jxch.capitals.stock4j.webapp.model.dto.StockPoolPriceChangeDailyParam;
import io.github.jxch.capitals.stock4j.webapp.model.dto.StockPoolPriceChangeDailyRes;
import reactor.core.publisher.Mono;

public interface StockPoolPriceChangeService {

    Mono<StockPoolPriceChangeDailyRes> daily(StockPoolPriceChangeDailyParam param);

}
