package io.github.jxch.capitals.stock4j.webapp.controller;

import io.github.jxch.capitals.stock4j.api.StockBatchParam;
import io.github.jxch.capitals.stock4j.api.StockBatchRes;
import io.github.jxch.capitals.stock4j.api.StockParam;
import io.github.jxch.capitals.stock4j.api.StockRes;
import io.github.jxch.capitals.stock4j.api3.lb.StockLBApi;
import io.github.jxch.capitals.stock4j.webapp.api.Stock4jApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("test")
    public static String test() {
        return "test";
    }

    @RequestMapping("hello")
    public String hello(Authentication authentication) {
//        JwtDecoder jwtDecoder = SpringUtil.getBean(JwtDecoder.class);
//        if (authorization != null && authorization.startsWith("Bearer ")) {
//            String token = authorization.substring(7); // 去掉 "Bearer " 前缀
//
//            Map<String, Object> claims = jwtDecoder.decode(token).getClaims();
//            log.info(JSON.toJSONString(claims));
//
//            return "Token: " + token;
//        }
        return "Hello World!";
    }

}
