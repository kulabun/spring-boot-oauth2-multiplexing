package com.labunco.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class GreetingController {
    @GetMapping("/greeting")
    public Map<String, Object> greeting() {
        return Collections.singletonMap("message", "Hello world!");
    }
}
