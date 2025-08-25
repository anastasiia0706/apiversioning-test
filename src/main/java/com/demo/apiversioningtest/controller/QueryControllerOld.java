package com.demo.apiversioningtest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryControllerOld {

    @GetMapping("/api/animals-old-query")
    public String getAnimalsByQuery(@RequestParam(value = "version", required = false) String version) {
        if ("v1".equals(version)) {
            return "V1 Old: Lion, Elephant (query)";
        } else if ("v2".equals(version)) {
            return "V2 Old: Lion Leo 2 years old, Elephant Ellie 10 years old (query)";
        } else {
            return "Default Old (query)";
        }
    }
}
