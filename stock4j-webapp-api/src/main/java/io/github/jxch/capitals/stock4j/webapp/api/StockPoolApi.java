package io.github.jxch.capitals.stock4j.webapp.api;

import io.github.jxch.capitals.cloud.common.api.CapitalsCloudServerNames;
import io.github.jxch.capitals.stock4j.model.StockPoolDto;
import io.github.jxch.capitals.valid.CreateGroup;
import io.github.jxch.capitals.valid.UpdateGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Validated
@RequestMapping("/stockPool")
@Tag(name = "股票池", description = "用户股票池增删改查")
@ReactiveFeignClient(name = CapitalsCloudServerNames.STOCK4J, fallbackFactory = CommonFallbackFactory.class)
public interface StockPoolApi {

    @PostMapping("findAll")
    @Operation(summary = "查询股票池", description = "根据用户id查询股票池")
    Mono<List<StockPoolDto>> findAll();

    @PostMapping("create")
    @Operation(summary = "新增股票池", description = "为该用户新增一个股票池, 并且返回已经创建的股票池")
    Mono<StockPoolDto> create(@RequestBody @Validated(CreateGroup.class) StockPoolDto stockPoolDto);

    @PostMapping("delete")
    @Operation(summary = "删除股票池", description = "根据股票池 id 删除该股票池")
    Mono<Void> delete(@NotNull(message = "股票池 id 不能为空") @RequestParam("id") Long id);

    @PostMapping("update")
    @Operation(summary = "更新股票池", description = "根据股票池 id 更新该股票池，然后返回更新后的结果")
    Mono<StockPoolDto> update(@RequestBody @Validated(UpdateGroup.class) StockPoolDto stockPoolDto);

}
