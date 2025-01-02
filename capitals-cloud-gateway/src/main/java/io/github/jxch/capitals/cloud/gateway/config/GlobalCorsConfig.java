package io.github.jxch.capitals.cloud.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Map;

@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "spring.cloud.gateway.globalcors")
public class GlobalCorsConfig {
    private Map<String, CorsConfiguration> corsConfigurations;

    @Bean
    @RefreshScope
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        if (corsConfigurations != null) {
            corsConfigurations.forEach(source::registerCorsConfiguration);
        }
        return source;
    }

}
