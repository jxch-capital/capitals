package io.github.jxch.capitals.stock4j.webapp.controller;

import io.github.jxch.capitals.stock4j.webapp.api.StockPoolApi;
import io.github.jxch.capitals.stock4j.webapp.dao.StockPoolDao;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StockPoolController implements StockPoolApi {
    private final StockPoolDao stockPoolDao;




}
