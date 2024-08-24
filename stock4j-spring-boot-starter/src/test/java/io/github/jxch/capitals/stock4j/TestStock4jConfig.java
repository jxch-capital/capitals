package io.github.jxch.capitals.stock4j;

import io.github.jxch.capitals.stock4j.config.Stock4jConfig;
import okhttp3.OkHttpClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.net.InetSocketAddress;
import java.net.Proxy;

@TestConfiguration
public class TestStock4jConfig {

    @Primary
    @Bean(Stock4jConfig.OK_HTTP_CLIENT)
    public OkHttpClient stock4jOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 10809)));
        return builder.build();
    }

}
