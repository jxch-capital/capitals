package io.github.jxch.capitals.chart.service.impl;

import cn.hutool.core.date.DateUtil;
import io.github.jxch.capitals.chart.core.KLineCharts;
import io.github.jxch.capitals.chart.model.KlineChartParam;
import io.github.jxch.capitals.chart.service.KlineChartService;
import io.github.jxch.capitals.chart.utils.KLineIndicators;
import io.github.jxch.capitals.stock4j.api.KLine;
import io.github.jxch.capitals.stock4j.api3.lb.StockLBApi;
import lombok.RequiredArgsConstructor;
import org.jfree.chart.JFreeChart;
import org.springframework.stereotype.Service;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

import java.awt.image.BufferedImage;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KlineChartServiceImpl implements KlineChartService {
    private final StockLBApi stockLBApi;

    @Override
    public BufferedImage klineChart(KlineChartParam param) {
        List<KLine> kLines = stockLBApi.query(param.getStockParam()
                .setStart(DateUtil.offset(param.getStockParam().getEnd(), param.getStockParam().getInterval().getDateField(),
                        -Math.max(param.getLength() * 3, 100)))).getKLines();

        kLines = kLines.subList(kLines.size() - param.getLength(), kLines.size());
        EMAIndicator ema20 = new EMAIndicator(new ClosePriceIndicator(KLineIndicators.kline2BarSeries(kLines)), 20);

        JFreeChart chart = KLineCharts.newKLineChart(kLines);
        KLineCharts.addLineMainIndex(chart, KLineIndicators.indicator2TimeSeriesCollection(ema20));
        KLineCharts.black(chart);
        KLineCharts.hideAxesAndLegend(chart);

        return chart.createBufferedImage(param.getWidth(), param.getHeight());
    }

}
