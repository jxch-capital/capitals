package io.github.jxch.capitals.crawler.common.config;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Slf4j
@Configuration
@ComponentScan("io.github.jxch.capitals.crawler.common")
public class CrawlerAutoConfig {
    public final static String OK_HTTP_CLIENT = "OK_HTTP_CLIENT";

    @Value("${stock4j.proxy.enable:false}")
    private boolean proxyEnable;
    @Value("${stock4j.proxy.type:HTTP}")
    private String proxyType;
    @Value("${stock4j.proxy.host:localhost}")
    private String proxyHost;
    @Value("${stock4j.proxy.port:10809}")
    private int proxyPort;

    @Bean(OK_HTTP_CLIENT)
    @ConditionalOnMissingBean(name = OK_HTTP_CLIENT)
    public OkHttpClient okHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (proxyEnable) {
            builder.proxy(new Proxy(Proxy.Type.valueOf(proxyType), new InetSocketAddress(proxyHost, proxyPort)));
            log.info("proxy enabled: {} {}:{}", proxyType, proxyHost, proxyPort);
        }

        return builder.build();
    }

}
