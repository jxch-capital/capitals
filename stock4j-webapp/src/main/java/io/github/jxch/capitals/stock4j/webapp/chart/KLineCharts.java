package io.github.jxch.capitals.stock4j.webapp.chart;

import io.github.jxch.capitals.stock4j.api.KLine;
import io.github.jxch.capitals.stock4j.webapp.util.KLineUtil;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;

import java.awt.*;
import java.text.DateFormat;
import java.util.Comparator;
import java.util.List;

public class KLineCharts {

    public static JFreeChart newKLineChart(List<KLine> kline) {
        return newKLineChart(kline, null);
    }

    public static JFreeChart newKLineChart(List<KLine> kline, String title) {
        OHLCSeries series = new OHLCSeries("K Line Series");
        kline.stream().sorted(Comparator.comparing(KLine::getDate))
                .forEach(kLine -> series.add(new Day(kLine.getDate()), kLine.getOpen(), kLine.getHigh(), kLine.getLow(), kLine.getClose()));

        OHLCSeriesCollection dataset = new OHLCSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createCandlestickChart(title, "Date", "Price", dataset, false);

        XYPlot plot = chart.getXYPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);

        // 设置纵轴范围，使其不从零开始
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setAutoRangeIncludesZero(false); // 默认会包含0，设置为false以排除0
        rangeAxis.setRange(KLineUtil.low(kline), KLineUtil.high(kline)); // 根据实际数据调整范围

        return chart;
    }

    public static void dateAxis(JFreeChart chart, DateFormat dateFormat) {
        XYPlot plot = chart.getXYPlot();
        DateAxis dateAxis = (DateAxis) plot.getDomainAxis();
        dateAxis.setDateFormatOverride(dateFormat);
    }

    public static void black(JFreeChart chart) {
        XYPlot plot = chart.getXYPlot();
        plot.setOutlinePaint(Color.BLACK);
        plot.setBackgroundPaint(Color.BLACK);
        chart.setBackgroundPaint(Color.BLACK);
        plot.setDomainGridlinePaint(Color.DARK_GRAY);
        plot.setRangeGridlinePaint(Color.DARK_GRAY);

        plot.setOutlineVisible(false); // 确保绘图区无边框
        chart.setBorderVisible(false); // 确保整个图表无边框

        CandlestickRenderer renderer = new CandlestickRenderer();
        renderer.setSeriesPaint(0, Color.GRAY); // 蜡烛填充色
        renderer.setDownPaint(new Color(192, 80, 77)); // 下跌填充色
        renderer.setUpPaint(new Color(155, 187, 89));   // 上涨填充色
        plot.setRenderer(renderer);
    }

    public static void hideAxesAndLegend(JFreeChart chart) {
        XYPlot plot = chart.getXYPlot();
        plot.getDomainAxis().setLabel(null);
        plot.getDomainAxis().setVisible(false);
        plot.getDomainAxis().setTickLabelsVisible(false);
        plot.getRangeAxis().setLabel(null);
        plot.getRangeAxis().setVisible(false);
        plot.getRangeAxis().setTickLabelsVisible(false);
        chart.removeLegend();
    }

    public static void addLineMainIndex(JFreeChart chart, TimeSeriesCollection timeSeries) {
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer lineRenderer = new XYLineAndShapeRenderer(true, false);
        lineRenderer.setSeriesPaint(0, Color.ORANGE);
        plot.setDataset(1, timeSeries);
        plot.setRenderer(1, lineRenderer);
    }

}
