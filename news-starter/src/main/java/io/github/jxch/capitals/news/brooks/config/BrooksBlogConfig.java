package io.github.jxch.capitals.news.brooks.config;

import lombok.Data;
import okhttp3.Request;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Data
@Configuration
@ConfigurationProperties(prefix = "brooks")
public class BrooksBlogConfig {
    public static final String BLOG_REQ = "brooksBlogRequest";
    public static final String ART_REQ = "brooksBlogArticleRequest";
    private String blog = "https://www.brookstradingcourse.com/price-action-trading-blog/page/";
    private String cookie = "shield-notbot-nonce=afb8947417";

    @Bean(BLOG_REQ)
    public Function<Integer, Request> brooksBlogRequest() {
        return (page) -> new Request.Builder()
                .url(blog + page)
                .addHeader("Cookie", cookie)
                .get()
                .build();
    }

    @Bean(ART_REQ)
    public Function<String, Request> brooksBlogArticleRequest() {
        return (url) -> new Request.Builder()
                .url(url)
                .addHeader("Cookie", cookie)
                .get()
                .build();
    }

}
