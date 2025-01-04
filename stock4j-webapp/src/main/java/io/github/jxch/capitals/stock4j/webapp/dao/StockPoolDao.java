package io.github.jxch.capitals.stock4j.webapp.dao;

import io.github.jxch.capitals.stock4j.webapp.model.entity.StockPool;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.List;

public interface StockPoolDao extends ReactiveCrudRepository<StockPool, Long> {

    Mono<List<StockPool>> findByUserid(String userid);

}
