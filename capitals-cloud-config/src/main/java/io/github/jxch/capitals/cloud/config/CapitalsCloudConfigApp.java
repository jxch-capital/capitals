package io.github.jxch.capitals.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class CapitalsCloudConfigApp {
    public static void main(String[] args) {
        SpringApplication.run(CapitalsCloudConfigApp.class, args);
    }
}