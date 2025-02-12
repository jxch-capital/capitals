package io.github.jxch.capitals.stock4j.webapp.api;

import io.github.jxch.capitals.cloud.common.api.CapitalsCloudServerNames;
import io.github.jxch.capitals.stock4j.model.StockNameAliasDto;
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
@RequestMapping("/stockNameAlias")
@Tag(name = "股票别名", description = "股票别名增删改查")
@ReactiveFeignClient(name = CapitalsCloudServerNames.STOCK4J, fallbackFactory = CommonFallbackFactory.class)
public interface StockNameAliasApi {

    @PostMapping("findAll")
    @Operation(summary = "查询当前用户定义的所有股票别名", description = "根据用户id查询股票别名")
    Mono<List<StockNameAliasDto>> findAll();

    @PostMapping("delete")
    @Operation(summary = "删除股票别名", description = "根据主键 id 删除该股票别名")
    Mono<Void> delete(@NotNull(message = "主键 id 不能为空") @RequestParam("id") Long id);

    @PostMapping("create")
    @Operation(summary = "新增股票别名", description = "为该用户新增一个股票别名, 并且返回已经创建的股票别名")
    Mono<StockNameAliasDto> create(@RequestBody @Validated(CreateGroup.class) StockNameAliasDto stockNameAliasDto);

    @PostMapping("update")
    @Operation(summary = "更新股票别名", description = "根据主键 id 更新该股票别名，然后返回更新后的结果")
    Mono<StockNameAliasDto> update(@RequestBody @Validated(UpdateGroup.class) StockNameAliasDto stockNameAliasDto);

}
