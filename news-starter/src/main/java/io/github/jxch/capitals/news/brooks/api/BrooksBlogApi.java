package io.github.jxch.capitals.news.brooks.api;

import io.github.jxch.capitals.crawler.common.config.CrawlerAutoConfig;
import io.github.jxch.capitals.news.CapitalsNewApi;
import io.github.jxch.capitals.news.brooks.config.BrooksBlogConfig;
import io.github.jxch.capitals.news.brooks.model.BrooksBlogParam;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BrooksBlogApi implements CapitalsNewApi {
    @Resource
    @Qualifier(CrawlerAutoConfig.OK_HTTP_CLIENT)
    private OkHttpClient okHttpClient;
    @Resource
    @Qualifier(BrooksBlogConfig.BLOG_REQ)
    private Function<Integer, Request> brooksBlogRequest;
    @Resource
    @Qualifier(BrooksBlogConfig.ART_REQ)
    private Function<String, Request> brooksBlogArticleRequest;

    @SneakyThrows
    public List<String> articleUrlsInPage(BrooksBlogParam param) {
        try (Response response = okHttpClient.newCall(brooksBlogRequest.apply(param.getPage())).execute()) {
            return Jsoup.parse(Objects.requireNonNull(response.body()).string())
                    .select("main.content article").stream()
                    .filter(article -> param.getContainsPinned() || !article.hasClass("sticky"))
                    .map(article -> article.selectFirst("header.entry-header"))
                    .filter(Objects::nonNull)
                    .map(header -> header.selectFirst("h2.entry-title[itemprop=headline]"))
                    .filter(Objects::nonNull)
                    .map(h2 -> h2.selectFirst("a.entry-title-link"))
                    .filter(Objects::nonNull)
                    .map(a -> a.attr("href"))
                    .collect(Collectors.toList());
        }
    }

    public String topArticleUrl(int index) {
        return Optional.ofNullable(articleUrlsInPage(BrooksBlogParam.builder().page(1).containsPinned(false).build()).get(index))
                .orElseThrow(() -> new RuntimeException("找不到文章"));
    }

    public String newArticleUrl() {
        return topArticleUrl(0);
    }

    @SneakyThrows
    public String articleHtml(String url) {
        try (Response response = okHttpClient.newCall(brooksBlogArticleRequest.apply(url)).execute()) {
            return Optional.ofNullable(Jsoup.parse(Objects.requireNonNull(response.body()).string())
                            .selectFirst("main.content article"))
                    .orElseThrow(() -> new IllegalArgumentException("这个URL中不包含文章: " + url))
                    .html();
        }
    }

    public String newArticleHtml() {
        for (int i = 0; i < 20; i++) {
            String articleHtml = articleHtml(topArticleUrl(i));
            if (articleHtml.contains("Please log in to view content.")) {
                continue;
            }
            return articleHtml;
        }

        throw new IllegalArgumentException("前20篇文章都需要登录才能访问，请核实文章源是否有效");
    }

    @SneakyThrows
    public URL newArticleFirstChartUrl() {
        Document doc = Jsoup.parse(newArticleHtml());
        Elements img = doc.select("img");
        if (!img.isEmpty()) {
            return new URL(img.get(0).attr("src"));
        }
        return null;
    }

}
