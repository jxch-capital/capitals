package io.github.jxch.capitals.stock4j.webapp.api;

import io.github.jxch.capitals.cloud.common.CloudAppsName;
import io.github.jxch.capitals.stock4j.api.StockBatchParam;
import io.github.jxch.capitals.stock4j.api.StockBatchRes;
import io.github.jxch.capitals.stock4j.api.StockParam;
import io.github.jxch.capitals.stock4j.api.StockRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/stock4j")
@FeignClient(name = CloudAppsName.Stock4j, fallback = Stock4jApiFallback.class)
public interface Stock4jApi {

    @ResponseBody
    @PostMapping("query")
    StockRes query(@RequestBody StockParam param);

    @ResponseBody
    @PostMapping("queryBatch")
    StockBatchRes query(@RequestBody StockBatchParam param);

}
