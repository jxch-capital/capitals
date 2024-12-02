package io.github.jxch.capitals.stock4j.api3;

import lombok.Data;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@Data
@AutoConfiguration
@ComponentScan
public class Stock4jApi3Config {
    public final static String STOCK_API = "CAPITAL_STOCK4J_API";
    public final static String OK_HTTP_CLIENT = "OK_HTTP_CLIENT";

    @Bean(OK_HTTP_CLIENT)
    @ConditionalOnMissingBean(name = OK_HTTP_CLIENT)
    public OkHttpClient stock4jOkHttpClient() {
        return new OkHttpClient.Builder().build();
    }

}
