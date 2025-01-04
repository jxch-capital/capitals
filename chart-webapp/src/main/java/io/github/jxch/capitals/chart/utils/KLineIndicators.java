package io.github.jxch.capitals.chart.utils;

import io.github.jxch.capitals.stock4j.model.KLine;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeries;
import org.ta4j.core.Indicator;
import org.ta4j.core.num.Num;

import java.time.Duration;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class KLineIndicators {
    public static BarSeries kline2BarSeries(List<KLine> kLines) {
        BarSeries series = new BaseBarSeries();
        kLines.stream().sorted(Comparator.comparing(KLine::getDate))
                .forEach(kLine -> series.addBar(new BaseBar(Duration.ofDays(1), kLine.getDate().toInstant().atZone(ZoneId.systemDefault()),
                        kLine.getOpen(), kLine.getHigh(), kLine.getLow(), kLine.getClose(), kLine.getVolume())));
        return series;
    }

    public static TimeSeriesCollection indicator2TimeSeriesCollection(Indicator<Num> indicator) {
        TimeSeries timeSeries = new TimeSeries("indicator series");
        BarSeries barSeries = indicator.getBarSeries();

        for (int i = 0; i < barSeries.getBarCount(); i++) {
            Date date = Date.from(barSeries.getBar(i).getEndTime().toInstant());
            Num value = indicator.getValue(i);

            timeSeries.add(new Day(date), value.doubleValue());
        }

        TimeSeriesCollection collection = new TimeSeriesCollection();
        collection.addSeries(timeSeries);

        return collection;
    }
}
