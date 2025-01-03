package io.github.jxch.capitals.stock4j.api3.yahoo.chart;

import com.alibaba.fastjson2.JSONObject;
import io.github.jxch.capitals.stock4j.api.StockBatchParam;
import io.github.jxch.capitals.stock4j.api.StockInterval;
import io.github.jxch.capitals.stock4j.api.StockParam;
import io.github.jxch.capitals.stock4j.api.StockRes;
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
public class YahooChartApi  implements YahooStockApi {
    private final OkHttpClient client;
    private final Supplier<HttpUrl.Builder> url;
    private final Supplier<Request.Builder> request;
    private final YahooChartConvert yahooChartConvert;

    public YahooChartApi(@Qualifier(Stock4jApi3Config.OK_HTTP_CLIENT) OkHttpClient client,
                         @Qualifier(YahooConfig.NEW_YAHOO_URL_CHART) Supplier<HttpUrl.Builder> url,
                         @Qualifier(YahooConfig.NEW_YAHOO_REQUEST) Supplier<Request.Builder> request,
                         YahooChartConvert yahooChartConvert) {
        this.client = client;
        this.url = url;
        this.request = request;
        this.yahooChartConvert = yahooChartConvert;
    }

    @SneakyThrows
    public YahooChartUrlRes chart(YahooChartUrlParam param) {
        try (Response response = client.newCall(param.newRequest(request, url)).execute()) {
            return JSONObject.parseObject(Objects.requireNonNull(response.body()).string(), YahooChartUrlRes.class).setCode(param.getCode());
        }
    }

    @Override
    public StockRes query(StockParam param) {
        return yahooChartConvert.toStockRes(chart(yahooChartConvert.toYahooChartUrlParam(param)));
    }

    @Override
    public boolean support(StockParam param) {
        return YahooStockApi.super.support(param) && !Objects.equals(param.getInterval(), StockInterval.REAL);
    }

    @Override
    public boolean support(StockBatchParam param) {
        return YahooStockApi.super.support(param) && !Objects.equals(param.getInterval(), StockInterval.REAL);
    }

}
