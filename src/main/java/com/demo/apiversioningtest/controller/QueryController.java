package com.demo.apiversioningtest.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/animals-query")
public class QueryController {

    @GetMapping
    public String getAnimalsDefault() { return "Default (query)"; }

    @GetMapping(version = "v1")
    public String getAnimalsV1() { return "V1: Lion, Elephant (query)"; }

    @GetMapping(version = "v2")
    public String getAnimalsV2() { return "V2: Lion Leo 2 years old, Elephant Ellie 10 years old (query)"; }

    @GetMapping(version = "v3")
    public String getAnimalsV3() { return "V3: This version is not in the list of supported versions  (query)"; }

    @GetMapping(version = "v1.1")
    public String getAnimalsV1_1() { return "V1.1: Lion, Elephant - Africa (query)"; }

    @GetMapping(value = "/{id}", version = "v1")
    public String getAnimalV1(@PathVariable int id) { return "V1: Animal ID " + id + " Lion (query)"; }

    @PostMapping(version = "v1")
    public String addAnimalV1(@RequestBody String name) { return "V1: Created animal: " + name + " (query)"; }

    @PutMapping(value = "/{id}", version = "v1")
    public String updateAnimalV1(@PathVariable int id, @RequestBody String animalName) { return "V1: Updated animal ID " + id + " to " + animalName + " (query)"; }

    @PatchMapping(value = "/{id}", version = "v1")
    public String patchAnimalV1(@PathVariable int id, @RequestBody String animalName) { return "V1: Patched animal ID " + id + " with " + animalName + " (query)"; }

    @DeleteMapping(value = "/{id}", version = "v1")
    public String deleteAnimalV1(@PathVariable int id) { return "V1: Deleted animal ID " + id + " (query)"; }
}