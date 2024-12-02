package io.github.jxch.capitals.chart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@EnableCaching
@ComponentScan("io.github.jxch")
@SpringBootApplication
public class ChartWebApp {

    public static void main(String[] args) {
        SpringApplication.run(ChartWebApp.class, args);
    }

}