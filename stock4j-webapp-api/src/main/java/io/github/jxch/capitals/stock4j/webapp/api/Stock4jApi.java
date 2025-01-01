package io.github.jxch.capitals.stock4j.webapp.api;

import io.github.jxch.capitals.stock4j.api.StockBatchParam;
import io.github.jxch.capitals.stock4j.api.StockBatchRes;
import io.github.jxch.capitals.stock4j.api.StockParam;
import io.github.jxch.capitals.stock4j.api.StockRes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@RequestMapping("/stock4j")
@ReactiveFeignClient(name = "Capitals-Stock4j", fallback = Stock4jApiFallback.class)
public interface Stock4jApi {

    @PostMapping("query")
    Mono<StockRes> query(@RequestBody StockParam param);

    @PostMapping("queryBatch")
    Mono<StockBatchRes> query(@RequestBody StockBatchParam param);

}
