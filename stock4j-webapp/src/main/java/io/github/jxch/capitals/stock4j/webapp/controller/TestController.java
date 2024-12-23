package io.github.jxch.capitals.stock4j.webapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class TestController {

//    @RequestMapping("/error")
//    public String error() {
//        return "error";
//    }

    @RequestMapping("/home")
    public ResponseEntity<String> home(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.ok("Authenticated as " + authentication.getName());
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not Authenticated");
    }

}
