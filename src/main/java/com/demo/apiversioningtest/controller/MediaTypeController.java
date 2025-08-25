package com.demo.apiversioningtest.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/animals-media")
public class MediaTypeController {

    @GetMapping
    public String getAnimalsDefaultVersionIsSetToV2() { return "V2: Lion Leo 5 years old, Elephant Ellie 12 years old (media)"; }

    @GetMapping(version = "v1")
    public String getAnimalsV1() { return "V1: Lion, Elephant (media)"; }

    @GetMapping(version = "v2")
    public String getAnimalsV2() { return "V2: Lion Leo 5 years old, Elephant Ellie 12 years old (media)" ;}

    @GetMapping(version = "v1.1")
    public String getAnimalsV1_1() {
        return "V1.1: Lion, Elephant - Africa (media)";
    }

    @GetMapping(value = "/{id}", version = "v1")
    public String getAnimalV1(@PathVariable int id) {
        return "V1: Animal ID " + id + " Lion (media)";
    }

    @PostMapping(version = "v1")
    public String addAnimalV1(@RequestBody String animalName) { return "V1: Created animal: " + animalName + " (media)"; }

    @PutMapping(value = "/{id}", version = "v1")
    public String updateAnimalV1(@PathVariable int id, @RequestBody String animalName) { return "V1: Updated animal ID " + id + " to " + animalName + " (media)"; }

    @PatchMapping(value = "/{id}", version = "v1")
    public String patchAnimalV1(@PathVariable int id, @RequestBody String animalName) { return "V1: Patched animal ID " + id + " with " + animalName + " (media)"; }

    @DeleteMapping(value = "/{id}", version = "v1")
    public String deleteAnimalV1(@PathVariable int id) { return "V1: Deleted animal ID " + id + " (media)"; }
}