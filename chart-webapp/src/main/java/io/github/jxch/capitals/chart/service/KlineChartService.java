package io.github.jxch.capitals.chart.service;

import io.github.jxch.capitals.chart.model.KlineChartParam;

import java.awt.image.BufferedImage;

public interface KlineChartService {

    BufferedImage klineChart(KlineChartParam param);

}
