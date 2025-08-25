package com.demo.apiversioningtest.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/{version}/animals-path") //Note: Remove {version} later, it should work automatically with configurer.usePathSegment(1)
public class PathController {

    @GetMapping
    public String getAnimalsDefault() {
        return "Default (path)";
    }

    @GetMapping(version = "v1")
    public String getAnimalsV1() { return "V1: Lion, Elephant (path)"; }

    @GetMapping(version = "v2")
    public String getAnimalsV2() {
        return "V2: Lion Leo 2 years old, Elephant Ellie 10 years old (path)";
    }

    @GetMapping(version = "v1.1")
    public String getAnimalsV1_1() {
        return "V1.1: Lion, Elephant - Africa (path)";
    }

    @GetMapping(value = "/{id}", version = "v1")
    public String getAnimalV1(@PathVariable int id) {
        return "V1: Animal ID " + id + " Lion (path)";
    }

    @PostMapping(version = "v1")
    public String addAnimalV1(@RequestBody String animalName) { return "V1: Created animal: " + animalName + " (path)"; }

    @PutMapping(value = "/{id}", version = "v1")
    public String updateAnimalV1(@PathVariable int id, @RequestBody String animalName) { return "V1: Updated animal ID " + id + " to " + animalName + " (path)"; }

    @PatchMapping(value = "/{id}", version = "v1")
    public String patchAnimalV1(@PathVariable int id, @RequestBody String animalName) { return "V1: Patched animal ID " + id + " with " + animalName + " (path)"; }

    @DeleteMapping(value = "/{id}", version = "v1")
    public String deleteAnimalV1(@PathVariable int id) { return "V1: Deleted animal ID " + id + " (path)"; }
}
