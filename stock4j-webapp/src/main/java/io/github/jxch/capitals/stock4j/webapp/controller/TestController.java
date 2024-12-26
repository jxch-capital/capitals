package io.github.jxch.capitals.stock4j.webapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class TestController {

    @RequestMapping("/home")
    public ResponseEntity<String> home() {
        log.info("home");
        return ResponseEntity.ok("home");
    }

}
