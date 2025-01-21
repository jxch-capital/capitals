package io.github.jxch.capitals.stock4j.webapp.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@OpenAPIDefinition(info = @Info(title = "Stock4j", version = "v1.0", description = "Stock4j API 文档"))
public class Stock4jApiConfig {
}
