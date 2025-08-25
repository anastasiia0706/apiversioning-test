package com.demo.apiversioningtest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MediaTypeControllerOld {

    @GetMapping("/api/animals-old-media")
    public String getAnimalsByMediaType(@RequestHeader(value = "Accept", required = false) String acceptHeader) {
        if (acceptHeader != null) {
            if (acceptHeader.contains("application/json;version=v1")) {
                return "V1 Old: Lion, Elephant (media)";
            } else if (acceptHeader.contains("application/json;version=v2")) {
                return "V2 Old: Lion Leo 2 years old, Elephant Ellie 10 years old (media)";
            }
        }
        return "Default Old (media)";
    }
}