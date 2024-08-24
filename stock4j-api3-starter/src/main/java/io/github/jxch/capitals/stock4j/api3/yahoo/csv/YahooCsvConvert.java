package io.github.jxch.capitals.stock4j.api3.yahoo.csv;

import io.github.jxch.capitals.stock4j.api.KLine;
import io.github.jxch.capitals.stock4j.api.StockInterval;
import io.github.jxch.capitals.stock4j.api.StockParam;
import io.github.jxch.capitals.stock4j.api.StockRes;
import org.mapstruct.Mapper;
import org.springframework.beans.BeanUtils;

import java.util.Comparator;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface YahooCsvConvert {

    default YahooCsvUrlParam toYahooCsvUrlParam(StockParam stockParam) {
        YahooCsvUrlParam param = YahooCsvUrlParam.builder()
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

    default StockRes toStockRes(YahooCsvRes res) {
        return StockRes.builder()
                .code(res.getCode())
                .kLines(res.getRes().stream().map(this::toKLine).sorted(Comparator.comparing(KLine::getDate)).toList())
                .build();
    }

    private KLine toKLine(YahooCsvUrlRes res) {
        KLine kLine = new KLine();
        BeanUtils.copyProperties(res, kLine);
        return kLine;
    }

}
