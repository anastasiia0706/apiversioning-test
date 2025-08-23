package com.demo.apiversioningtest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeaderControllerOld {

    @GetMapping("/api/animals-old-header")
    public String getAnimalsByHeader(@RequestHeader(value = "X-API-Version", required = false) String version) {
        if ("v1".equals(version)) {
            return "V1 Old: Lion, Elephant (header)";
        } else if ("v2".equals(version)) {
            return "V2 Old: Lion Leo 2 years old, Elephant Ellie 10 years old (header)";
        } else {
            return "Default Old (header)";
        }
    }
}
