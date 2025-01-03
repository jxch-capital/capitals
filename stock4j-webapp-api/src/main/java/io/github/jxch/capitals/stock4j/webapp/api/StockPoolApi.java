package io.github.jxch.capitals.stock4j.webapp.api;

import io.github.jxch.capitals.cloud.common.api.CapitalsCloudServerNames;
import org.springframework.web.bind.annotation.RequestMapping;
import reactivefeign.spring.config.ReactiveFeignClient;

@RequestMapping("/stockPool")
@ReactiveFeignClient(name = CapitalsCloudServerNames.STOCK4J, fallbackFactory = CommonFallbackFactory.class)
public interface StockPoolApi {



}
