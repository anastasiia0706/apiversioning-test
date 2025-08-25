package com.demo.apiversioningtest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test", version = "v1")
public class BasicTestController {

    @GetMapping("/basic")
    public String basicTest() {
        return "Basic test";
    }
}
