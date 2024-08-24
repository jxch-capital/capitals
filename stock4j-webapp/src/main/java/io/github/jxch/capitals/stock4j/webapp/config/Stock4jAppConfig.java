package io.github.jxch.capitals.stock4j.webapp.config;

import io.github.jxch.capitals.stock4j.api3.Stock4jApi3Config;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Configuration
public class Stock4jAppConfig {
    @Value("${stock4j.proxy.enable:false}")
    private boolean proxyEnable;
    @Value("${stock4j.proxy.type:HTTP}")
    private String proxyType;
    @Value("${stock4j.proxy.host:localhost}")
    private String proxyHost;
    @Value("${stock4j.proxy.port:10809}")
    private int proxyPort;

    @Primary
    @Bean(Stock4jApi3Config.OK_HTTP_CLIENT)
    public OkHttpClient stock4jOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (proxyEnable) {
            builder.proxy(new Proxy(Proxy.Type.valueOf(proxyType), new InetSocketAddress(proxyHost, proxyPort)));
        }

        return builder.build();
    }

}
