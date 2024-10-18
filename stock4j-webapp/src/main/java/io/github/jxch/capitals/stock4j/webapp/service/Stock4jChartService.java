package io.github.jxch.capitals.stock4j.webapp.service;

import io.github.jxch.capitals.stock4j.webapp.dto.StockChartParam;

import java.awt.image.BufferedImage;

public interface Stock4jChartService {

    BufferedImage klineChart(StockChartParam param);

}

                                           