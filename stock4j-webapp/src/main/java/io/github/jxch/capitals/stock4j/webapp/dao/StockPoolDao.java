package io.github.jxch.capitals.stock4j.webapp.dao;

import io.github.jxch.capitals.stock4j.webapp.model.entity.StockPool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockPoolDao extends JpaRepository<StockPool, Long> {
}
