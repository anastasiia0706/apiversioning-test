package com.demo.apiversioningtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class PathVersioningTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testGetAnimalsV1Old() throws Exception {
        mockMvc.perform(get("/api/v1/animals-old-path"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1 Old: Lion, Elephant (path)")));
    }

    @Test
    void testGetAnimalsV2Old() throws Exception {
        mockMvc.perform(get("/api/v2/animals-old-path"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V2 Old: Lion Leo 2 years old, Elephant Ellie 10 years old (path)")));
    }

    @Test
    void testGetAnimalsDefaultOld() throws Exception {
        mockMvc.perform(get("/api/animals-old-path"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Default Old (path)")));
    }

    @Test
    void testDefault() throws Exception {
        mockMvc.perform(get("/api/latest/animals-path"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Default (path)")));
    }

    @Test
    void testGetAnimalsV1() throws Exception {
        mockMvc.perform(get("/api/v1/animals-path"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1: Lion, Elephant (path)")));
    }

    @Test
    void testGetAnimalsV2() throws Exception {
        mockMvc.perform(get("/api/v2/animals-path"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V2: Lion Leo 2 years old, Elephant Ellie 10 years old (path)")));
    }

    @Test
    void testGetAnimalsV1_1() throws Exception {
        mockMvc.perform(get("/api/v1.1/animals-path"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1.1: Lion, Elephant - Africa (path)")));
    }

    @Test
    void testGetIndividualAnimalV1() throws Exception {
        mockMvc.perform(get("/api/v1/animals-path/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1: Animal ID 1 Lion (path)")));
    }

    @Test
    void testPostAnimalV1() throws Exception {
        mockMvc.perform(post("/api/v1/animals-path")
                        .content("Monkey")
                        .contentType("text/plain"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1: Created animal: Monkey (path)")));
    }

    @Test
    void testPutAnimalV1() throws Exception {
        mockMvc.perform(put("/api/v1/animals-path/1")
                        .content("Tiger")
                        .contentType("text/plain"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1: Updated animal ID 1 to Tiger (path)")));
    }

    @Test
    void testPatchAnimalV1() throws Exception {
        mockMvc.perform(patch("/api/v1/animals-path/1")
                        .content("Zebra")
                        .contentType("text/plain"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1: Patched animal ID 1 with Zebra (path)")));
    }

    @Test
    void testDeleteAnimalV1() throws Exception {
        mockMvc.perform(delete("/api/v1/animals-path/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1: Deleted animal ID 1 (path)")));
    }

    @Test
    void testInvalidPath() throws Exception {
        mockMvc.perform(get("/animals-path/v1/api"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testUnsupportedVersion() throws Exception {
        mockMvc.perform(get("/api/v99/animals-path"))
                .andExpect(status().is4xxClientError());
    }

}
