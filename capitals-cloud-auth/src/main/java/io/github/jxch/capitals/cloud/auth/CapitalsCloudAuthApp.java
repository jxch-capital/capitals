package io.github.jxch.capitals.cloud.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class CapitalsCloudAuthApp  {

    public static void main(String[] args) {
        SpringApplication.run(CapitalsCloudAuthApp.class, args);
    }

}