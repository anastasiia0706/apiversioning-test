package com.demo.apiversioningtest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathControllerOld {
    @GetMapping("/api/v1/animals-old-path")
    public String getAnimalsV1Old() {
        return "V1 Old: Lion, Elephant (path)";
    }

    @GetMapping("/api/v2/animals-old-path")
    public String getAnimalsV2Old() {
        return "V2 Old: Lion Leo 2 years old, Elephant Ellie 10 years old (path)";
    }

    @GetMapping("/api/animals-old-path")
    public String getAnimalsDefaultOld() {
        return "Default Old (path)";
    }

}
