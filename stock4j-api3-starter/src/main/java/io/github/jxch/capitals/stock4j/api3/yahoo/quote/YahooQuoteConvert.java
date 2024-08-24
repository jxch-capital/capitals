package io.github.jxch.capitals.stock4j.api3.yahoo.quote;

import io.github.jxch.capitals.stock4j.api.*;
import org.mapstruct.Mapper;

import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface YahooQuoteConvert {

    default YahooQuoteParam toYahooQuoteParam(StockParam stockParam) {
        return YahooQuoteParam.builder().symbols(List.of(stockParam.getCode())).build();
    }

    default StockRes toStockRes(YahooQuoteRes res) {
        YahooQuoteRes.QuoteResponseResultItem item = res.getQuoteResponse().getResult().get(0);
        return StockRes.builder()
                .code(item.getSymbol())
                .kLines(List.of(KLine.builder()
                        .date(new Date(item.getRegularMarketTime() * 1000))
                        .close(item.getRegularMarketPrice())
                        .open(item.getRegularMarketOpen())
                        .high(item.getRegularMarketDayHigh())
                        .low(item.getRegularMarketDayLow())
                        .volume(Double.valueOf(item.getRegularMarketVolume()))
                        .build()))
                .build();
    }

    default YahooQuoteParam toYahooQuoteParam(StockBatchParam param) {
        return YahooQuoteParam.builder().symbols(param.getCodes()).build();
    }

    default StockBatchRes toStockBatchRes(YahooQuoteRes source) {
        StockBatchRes res = new StockBatchRes();
        List<YahooQuoteRes.QuoteResponseResultItem> items = source.getQuoteResponse().getResult();
        YahooQuoteRes yahooQuoteRes = new YahooQuoteRes().setQuoteResponse(new YahooQuoteRes.QuoteResponse());
        for (YahooQuoteRes.QuoteResponseResultItem item : items) {
            yahooQuoteRes.getQuoteResponse().setResult(List.of(item));
            res.addByStockRes(toStockRes(yahooQuoteRes));
        }
        return res;
    }

}
