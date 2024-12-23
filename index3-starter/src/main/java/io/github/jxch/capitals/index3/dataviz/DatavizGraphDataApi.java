package io.github.jxch.capitals.index3.dataviz;

import com.alibaba.fastjson2.JSONObject;
import io.github.jxch.capitals.crawler.common.CrawlerAutoConfig;
import io.github.jxch.capitals.index3.api.MarketIndexApi;
import io.github.jxch.capitals.index3.MarketIndexAutoConfig;
import io.github.jxch.capitals.index3.dataviz.config.DatavizConfig;
import io.github.jxch.capitals.index3.dataviz.model.DatavizGraphDataRes;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Qualifier(MarketIndexAutoConfig.MARKET_INDEX_API)
public class DatavizGraphDataApi implements MarketIndexApi<Void, DatavizGraphDataRes> {
    @Resource
    @Qualifier(CrawlerAutoConfig.OK_HTTP_CLIENT)
    private OkHttpClient client;
    @Resource
    @Qualifier(DatavizConfig.DATAVIZ_GRAPH_DATA_REQUEST)
    private Request request;

    @SneakyThrows
    public DatavizGraphDataRes graphData() {
        try (Response response = client.newCall(request).execute()) {
            String jsonString = Objects.requireNonNull(response.body()).string();
            return JSONObject.parseObject(jsonString, DatavizGraphDataRes.class);
        }
    }

    @Override
    public DatavizGraphDataRes index(Void param) {
        return graphData();
    }

}
