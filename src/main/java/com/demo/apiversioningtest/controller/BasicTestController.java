package com.demo.apiversioningtest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class BasicTestController {

    // does not work if configurer.usePathSegment(1)
    @GetMapping("/basic")
    public String basicTest() {
        return "Basic test";
    }
}
