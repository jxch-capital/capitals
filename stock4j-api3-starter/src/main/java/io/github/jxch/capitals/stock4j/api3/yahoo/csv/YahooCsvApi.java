package io.github.jxch.capitals.stock4j.api3.yahoo.csv;

import cn.hutool.core.text.csv.CsvUtil;
import io.github.jxch.capitals.stock4j.api.StockBatchParam;
import io.github.jxch.capitals.stock4j.api.StockInterval;
import io.github.jxch.capitals.stock4j.api3.Stock4jApi3Config;
import io.github.jxch.capitals.stock4j.api3.yahoo.YahooConfig;
import io.github.jxch.capitals.stock4j.api3.yahoo.YahooStockApi;
import io.github.jxch.capitals.stock4j.api.StockParam;
import io.github.jxch.capitals.stock4j.api.StockRes;
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
public class YahooCsvApi implements YahooStockApi {
    private final OkHttpClient client;
    private final Supplier<HttpUrl.Builder> url;
    private final Supplier<Request.Builder> request;
    private final YahooCsvConvert yahooCsvConvert;

    public YahooCsvApi(@Qualifier(Stock4jApi3Config.OK_HTTP_CLIENT) OkHttpClient client,
                       @Qualifier(YahooConfig.NEW_YAHOO_URL_CSV) Supplier<HttpUrl.Builder> url,
                       @Qualifier(YahooConfig.NEW_YAHOO_REQUEST) Supplier<Request.Builder> request,
                       YahooCsvConvert yahooCsvConvert) {
        this.client = client;
        this.url = url;
        this.request = request;
        this.yahooCsvConvert = yahooCsvConvert;
    }

    @SneakyThrows
    public YahooCsvRes downloadStockCsv(YahooCsvUrlParam param) {
        try (Response response = client.newCall(param.newRequest(request, url)).execute()) {
            return YahooCsvRes.builder().code(param.getCode())
                    .res(CsvUtil.getReader().read(Objects.requireNonNull(response.body()).string(), YahooCsvUrlRes.class))
                    .build();
        }
    }

    @Override
    public StockRes query(StockParam param) {
        return yahooCsvConvert.toStockRes(downloadStockCsv(yahooCsvConvert.toYahooCsvUrlParam(param)));
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
