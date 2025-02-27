package io.github.jxch.capitals.stock4j.webapp.dao;

import io.github.jxch.capitals.stock4j.webapp.model.entity.StockPool;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

public interface StockPoolDao extends ReactiveCrudRepository<StockPool, Long> {

    Flux<StockPool> findByUserid(String userid);

    Flux<StockPool> findByUseridAndIdIsIn(String userid, Collection<Long> ids);

    Mono<Void> deleteByIdAndUserid(Long id, String userid);

}
