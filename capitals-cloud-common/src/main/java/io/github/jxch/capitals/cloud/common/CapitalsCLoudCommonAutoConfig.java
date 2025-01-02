package io.github.jxch.capitals.cloud.common;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;

@RefreshScope
@Configuration
@ComponentScan
@EnableRedisWebSession
public class CapitalsCLoudCommonAutoConfig {

}
