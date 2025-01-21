package io.github.jxch.capitals.stock4j.webapp.controller;

import io.github.jxch.capitals.cloud.common.api.CapitalsCloudServerNames;
import io.github.jxch.capitals.stock4j.webapp.api.CommonFallbackFactory;
import io.github.jxch.capitals.stock4j.webapp.model.dto.StockPoolPriceChangeDailyParam;
import io.github.jxch.capitals.stock4j.webapp.model.dto.StockPoolPriceChangeDailyRes;
import io.github.jxch.capitals.stock4j.webapp.service.StockPoolPriceChangeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/stockPool/priceChange")
@Tag(name = "股票池", description = "股票池价格变动接口")
@ReactiveFeignClient(name = CapitalsCloudServerNames.STOCK4J, fallbackFactory = CommonFallbackFactory.class)
@RequiredArgsConstructor
public class StockPoolPriceChangeController {
    private final StockPoolPriceChangeService stockPoolPriceChangeService;

    @PostMapping("daily")
    @Operation(summary = "日线价格变动查询", description = "根据日线查询股票池股票价格变动")
    public Mono<StockPoolPriceChangeDailyRes> daily(@RequestBody @Valid StockPoolPriceChangeDailyParam param) {
        return stockPoolPriceChangeService.daily(param);
    }

}
