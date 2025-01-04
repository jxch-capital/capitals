package io.github.jxch.capitals.stock4j.webapp.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.reactive.TransactionalOperator;

import java.time.LocalDate;

@Configuration
public class CacheKeyGeneratorConfig {
    public static final String INDEX3_BREATH_KEY_GENERATOR = "index3breathKeyGenerator";

    @Bean(name = INDEX3_BREATH_KEY_GENERATOR)
    public KeyGenerator index3breathKeyGenerator() {
        return (target, method, params) -> LocalDate.now() + params[0].toString();
    }



}
