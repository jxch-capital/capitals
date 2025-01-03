package io.github.jxch.capitals.index3.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "市场指数查询接口", description = "市场指数查询通用接口")
public interface MarketIndexApi<T, R> {

    @Operation(summary = "市场指数查询")
    R index(T param);



}