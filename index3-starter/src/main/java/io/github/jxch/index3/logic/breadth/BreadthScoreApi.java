package io.github.jxch.index3.logic.breadth;

import com.alibaba.fastjson2.JSONObject;
import io.github.jxch.capitals.crawler.common.config.CrawlerAutoConfig;
import io.github.jxch.index3.api.MarketIndexApi;
import io.github.jxch.index3.config.MarketIndexAutoConfig;
import io.github.jxch.index3.logic.breadth.config.BreadthScoreConfig;
import io.github.jxch.index3.logic.breadth.model.BreadthScoreRes;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Qualifier(MarketIndexAutoConfig.MARKET_INDEX_API)
public class BreadthScoreApi implements MarketIndexApi<Void, BreadthScoreRes> {
    @Resource
    @Qualifier(CrawlerAutoConfig.OK_HTTP_CLIENT)
    private OkHttpClient client;
    @Resource
    @Qualifier(BreadthScoreConfig.BREADTH_SCORE_REQUEST)
    private Request request;

    @SneakyThrows
    public BreadthScoreRes breadthScore() {
        try (Response response = client.newCall(request).execute()) {
            String html = Objects.requireNonNull(response.body()).string();
            String jsonString = Objects.requireNonNull(Jsoup.parse(html).select("script[data-for=river]").first()).data();
            return JSONObject.parseObject(jsonString, BreadthScoreRes.class);
        }
    }

    @Override
    public BreadthScoreRes index(Void param) {
        return breadthScore();
    }

}
