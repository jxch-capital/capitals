package io.github.jxch.capitals.cloud.common;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Map;

@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "spring.cloud.cors")
public class CorsConfig {
    private Map<String, CorsConfiguration> corsConfigurations;

    @Bean
    @RefreshScope
    @ConditionalOnMissingBean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        if (corsConfigurations != null) {
            corsConfigurations.forEach(source::registerCorsConfiguration);
        }
        return source;
    }

    @Bean
    public CorsWebFilter corsFilter(CorsConfigurationSource source) {
        return new CorsWebFilter(source);
    }

}
