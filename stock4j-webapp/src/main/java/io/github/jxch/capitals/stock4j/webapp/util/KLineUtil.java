package io.github.jxch.capitals.stock4j.webapp.util;

import io.github.jxch.capitals.stock4j.api.KLine;

import java.util.Comparator;
import java.util.List;

public class KLineUtil {

    public static double low(List<KLine> kline) {
        return kline.stream().min(Comparator.comparing(KLine::getLow)).orElseThrow().getLow();
    }

    public static double high(List<KLine> kline) {
        return kline.stream().max(Comparator.comparing(KLine::getLow)).orElseThrow().getHigh();
    }

}
