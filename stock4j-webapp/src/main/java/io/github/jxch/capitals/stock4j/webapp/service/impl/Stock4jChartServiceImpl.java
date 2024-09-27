package io.github.jxch.capitals.stock4j.webapp.service.impl;

import cn.hutool.core.date.DateUtil;
import io.github.jxch.capitals.stock4j.api.KLine;
import io.github.jxch.capitals.stock4j.api.StockParam;
import io.github.jxch.capitals.stock4j.api3.lb.StockLBApi;
import io.github.jxch.capitals.stock4j.webapp.chart.KLineCharts;
import io.github.jxch.capitals.stock4j.webapp.chart.KLineIndicators;
import io.github.jxch.capitals.stock4j.webapp.service.Stock4jChartService;
import lombok.RequiredArgsConstructor;
import org.jfree.chart.JFreeChart;
import org.springframework.stereotype.Service;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Stock4jChartServiceImpl implements Stock4jChartService {
    private final StockLBApi stockLBApi;

    @Override
    public BufferedImage klineChart() {
        List<KLine> kLines = stockLBApi.query(StockParam.builder().code("QQQ").start(DateUtil.offsetWeek(new Date(), -16)).build()).getKLines();

        EMAIndicator ema20 = new EMAIndicator(new ClosePriceIndicator(KLineIndicators.kline2BarSeries(kLines)), 20);

        JFreeChart chart = KLineCharts.newKLineChart(kLines);
        KLineCharts.addLineMainIndex(chart, KLineIndicators.indicator2TimeSeriesCollection(ema20));
        KLineCharts.black(chart);
        KLineCharts.hideAxesAndLegend(chart);

        return chart.createBufferedImage(800, 600);
    }
}
