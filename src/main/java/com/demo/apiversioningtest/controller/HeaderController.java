package com.demo.apiversioningtest.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/animals")
public class HeaderController {

    @GetMapping(version = "v1")
    public String getAnimalsV1() { return "V1: Lion, Elephant (header)"; }

    @GetMapping(version = "v2")
    public String getAnimalsV2() { return "Header V2: Lion Leo 5 years old in Savannah, Elephant Ellie 12 years old in Plains";}

    @GetMapping(version = "v1.1")
    public String getAnimalsV1_1() {
        return "V1.1: Lion, Elephant - Africa (header)";
    }

    @GetMapping(value = "/{id}", version = "v1")
    public String getAnimalV1(@PathVariable int id) {
        return "V1: Animal ID " + id + " Lion (header)";
    }

    @PostMapping(version = "v1")
    public String addAnimalV1(@RequestBody String animalName) { return "V1: Created animal: " + animalName + " (header)"; }

    @GetMapping
    public String getAnimalsDefault() {
        return "Default (header)";
    }
}