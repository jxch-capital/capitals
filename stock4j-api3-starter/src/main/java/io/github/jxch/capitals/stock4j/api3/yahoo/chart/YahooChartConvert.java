package io.github.jxch.capitals.stock4j.api3.yahoo.chart;

import io.github.jxch.capitals.stock4j.model.KLine;
import io.github.jxch.capitals.stock4j.model.StockParam;
import io.github.jxch.capitals.stock4j.model.StockRes;
import io.github.jxch.capitals.stock4j.type.StockInterval;
import org.mapstruct.Mapper;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.IntStream;

@Mapper(componentModel = "spring")
public interface YahooChartConvert {

    default YahooChartUrlParam toYahooChartUrlParam(StockParam stockParam) {
        YahooChartUrlParam param = YahooChartUrlParam.builder()
                .code(stockParam.getCode())
                .start(stockParam.getStart())
                .end(stockParam.getEnd())
                .build();

        if (Objects.requireNonNull(stockParam.getInterval()) == StockInterval.DAY_1) {
            param.setInterval("1d");
        } else {
            throw new IllegalArgumentException("不支持的时间级别");
        }

        return param;
    }

    default StockRes toStockRes(YahooChartUrlRes res) {
        return StockRes.builder()
                .code(res.getCode())
                .kLines(IntStream.range(0, res.getLengthThrow()).parallel().mapToObj(index -> new KLine()
                                .setDate(res.getDate(index))
                                .setClose(res.getClose(index))
                                .setOpen(res.getOpen(index))
                                .setLow(res.getLow(index))
                                .setHigh(res.getHigh(index))
                                .setVolume(res.getVolume(index)))
                        .filter(kLine -> Objects.nonNull(kLine.getClose()))
                        .sorted(Comparator.comparing(KLine::getDate)).toList())
                .build();
    }

}
