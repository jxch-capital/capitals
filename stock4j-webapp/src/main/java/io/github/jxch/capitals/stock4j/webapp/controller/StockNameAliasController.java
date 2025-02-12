package io.github.jxch.capitals.stock4j.webapp.controller;

import io.github.jxch.capitals.stock4j.model.StockNameAliasDto;
import io.github.jxch.capitals.stock4j.webapp.api.StockNameAliasApi;
import io.github.jxch.capitals.stock4j.webapp.service.StockNameAliasService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StockNameAliasController implements StockNameAliasApi {
    private final StockNameAliasService stockNameAliasService;

    @Override
    public Mono<List<StockNameAliasDto>> findAll() {
        return stockNameAliasService.findAll();
    }

    @Override
    public Mono<Void> delete(Long id) {
        return stockNameAliasService.delete(id);
    }

    @Override
    public Mono<StockNameAliasDto> create(StockNameAliasDto stockNameAliasDto) {
        return stockNameAliasService.create(stockNameAliasDto);
    }

    @Override
    public Mono<StockNameAliasDto> update(StockNameAliasDto stockNameAliasDto) {
        return stockNameAliasService.update(stockNameAliasDto);
    }

}
