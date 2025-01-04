package io.github.jxch.capitals.stock4j.webapp.api;

import io.github.jxch.capitals.cloud.common.api.CapitalsCloudServerNames;
import io.github.jxch.capitals.stock4j.model.StockBatchParam;
import io.github.jxch.capitals.stock4j.model.StockBatchRes;
import io.github.jxch.capitals.stock4j.model.StockParam;
import io.github.jxch.capitals.stock4j.model.StockRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RequestMapping("/stock4j")
@Tag(name = "股票", description = "股票信息查询接口")
@ReactiveFeignClient(name = CapitalsCloudServerNames.STOCK4J, fallbackFactory = CommonFallbackFactory.class)
public interface Stock4jApi {

    @PostMapping("query")
    @Operation(summary = "单只股票历史K线", description = "查询单只股票的历史价格K线")
    Mono<StockRes> query(@RequestBody StockParam param);

    @PostMapping("queryBatch")
    @Operation(summary = "批量股票历史K线", description = "查询批量股票的历史价格K线")
    Mono<StockBatchRes> query(@RequestBody StockBatchParam param);

    @GetMapping("/engines")
    @Operation(summary = "股票引擎", description = "不同的查询引擎可能股票代码的格式不同")
    Mono<List<String>> stockEngines();

}
