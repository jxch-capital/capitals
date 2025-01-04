package io.github.jxch.capitals.stock4j.webapp.controller;

import io.github.jxch.capitals.stock4j.model.StockPoolDto;
import io.github.jxch.capitals.stock4j.webapp.api.StockPoolApi;
import io.github.jxch.capitals.stock4j.webapp.service.StockPoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StockPoolController implements StockPoolApi {
    private final StockPoolService stockPoolService;

    @Override
    public Mono<List<StockPoolDto>> findAll() {
        return stockPoolService.findAll();
    }

    @Override
    public Mono<StockPoolDto> create(StockPoolDto stockPoolDto) {
        return stockPoolService.create(stockPoolDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return stockPoolService.delete(id);
    }

    @Override
    public Mono<StockPoolDto> update(StockPoolDto stockPoolDto) {
        return stockPoolService.update(stockPoolDto);
    }

}
