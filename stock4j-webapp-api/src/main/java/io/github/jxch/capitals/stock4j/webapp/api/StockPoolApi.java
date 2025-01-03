package io.github.jxch.capitals.stock4j.webapp.api;

import org.springframework.web.bind.annotation.RequestMapping;
import reactivefeign.spring.config.ReactiveFeignClient;

@RequestMapping("/stockPool")
@ReactiveFeignClient(name = "Capitals-Stock4j", fallbackFactory = CommonFallbackFactory.class)
public interface StockPoolApi {



}
