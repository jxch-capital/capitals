package io.github.jxch.capitals.chart.service;

import io.github.jxch.capitals.chart.model.BreathParam;
import io.github.jxch.capitals.chart.model.FearGreedParam;

import java.awt.image.BufferedImage;

public interface Index3ChartService {

    BufferedImage breath(BreathParam param);

    BufferedImage fearGreed(FearGreedParam param);

}
