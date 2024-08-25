package io.github.jxch.cloud.devtools;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Data
@Configuration
@Profile(AppEnv.DEV)
public class DevToolsConfig {
    @Value("${devtools.local-enable:false}")
    private boolean localEnable;
    @Value("${devtools.local-dev:local}")
    private String localDev;
    @Value("${devtools.source-service:${spring.application.name}}")
    private String sourceService;
    @Value("${devtools.target-service:${spring.application.name}-${devtools.local-dev}}")
    private String targetService;
}
