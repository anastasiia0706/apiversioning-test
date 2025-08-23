package com.demo.apiversioningtest.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/{version}/animals") //Note: Remove {version} later, it should work automatically with configurer.usePathSegment(1)
public class PathController {

    @GetMapping(version = "v1")
    public String getAnimalsV1() {return "V1: Lion, Elephant (path)";}

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
    public String addAnimalV1(@RequestBody String animalName) {
        return "V1: Created animal: " + animalName + " (path)";
    }

    // Note: Does not work now, but should work when removing {version} from @RequestMapping
    @GetMapping
    public String getAnimalsDefault() {
        return "Default (path)";
    }

    // Note: {"v1", "v2"} is red, is not supported by IDEA yet
    /*
    @GetMapping(value = "/test", version = {"v1", "v2"})
    public String getMultipleVersions() {
        return "V1 and V2 (path)";
    }
    */
}
