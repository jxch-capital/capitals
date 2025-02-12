package io.github.jxch.capitals.stock4j.webapp.dao;

import io.github.jxch.capitals.stock4j.webapp.model.entity.StockNameAlias;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StockNameAliasDao extends ReactiveCrudRepository<StockNameAlias, Long> {

    Flux<StockNameAlias> findByUserid(String userid);

    Mono<Void> deleteByIdAndUserid(Long id, String userid);

}
