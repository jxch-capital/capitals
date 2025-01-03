package io.github.jxch.capitals.stock4j.webapp.dao;

import io.github.jxch.capitals.stock4j.webapp.model.entity.StockPool;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StockPoolDao extends ReactiveCrudRepository<StockPool, Long> {
}
