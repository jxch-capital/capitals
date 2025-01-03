package io.github.jxch.capitals.stock4j.api3.yahoo.quote;

import com.alibaba.fastjson2.JSONObject;
import io.github.jxch.capitals.stock4j.api.*;
import io.github.jxch.capitals.stock4j.api3.Stock4jApi3Config;
import io.github.jxch.capitals.stock4j.api3.yahoo.YahooConfig;
import io.github.jxch.capitals.stock4j.api3.yahoo.YahooStockApi;
import lombok.SneakyThrows;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Supplier;

@Component
@Qualifier(YahooConfig.YAHOO_API)
public class YahooQuoteApi implements YahooStockApi {
    private final OkHttpClient client;
    private final Supplier<HttpUrl.Builder> url;
    private final Supplier<Request.Builder> request;
    private final YahooQuoteConvert yahooQuoteConvert;

    public YahooQuoteApi(@Qualifier(Stock4jApi3Config.OK_HTTP_CLIENT) OkHttpClient client,
                         @Qualifier(YahooConfig.NEW_YAHOO_URL_QUOTE) Supplier<HttpUrl.Builder> url,
                         @Qualifier(YahooConfig.NEW_YAHOO_REQUEST) Supplier<Request.Builder> request,
                         YahooQuoteConvert yahooQuoteConvert) {
        this.client = client;
        this.url = url;
        this.request = request;
        this.yahooQuoteConvert = yahooQuoteConvert;
    }

    @SneakyThrows
    public YahooQuoteRes quote(YahooQuoteParam param) {
        try (Response response = client.newCall(param.newRequest(request, url)).execute()) {
            return JSONObject.parseObject(Objects.requireNonNull(response.body()).string(), YahooQuoteRes.class);
        }
    }

    @Override
    public StockRes query(StockParam param) {
        return yahooQuoteConvert.toStockRes(quote(yahooQuoteConvert.toYahooQuoteParam(param)));
    }

    @Override
    public StockBatchRes query(StockBatchParam param) {
        return yahooQuoteConvert.toStockBatchRes(quote(yahooQuoteConvert.toYahooQuoteParam(param)));
    }

    @Override
    public boolean support(StockParam param) {
        return YahooStockApi.super.support(param) && Objects.equals(param.getInterval(), StockInterval.REAL);
    }

    @Override
    public boolean support(StockBatchParam param) {
        return YahooStockApi.super.support(param) && Objects.equals(param.getInterval(), StockInterval.REAL);
    }

}
