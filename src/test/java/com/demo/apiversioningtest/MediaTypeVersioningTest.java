package com.demo.apiversioningtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class MediaTypeVersioningTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAnimalsV1Old() throws Exception {
        mockMvc.perform(get("/api/animals-old-media")
                        .header("Accept", "application/json;version=v1"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1 Old: Lion, Elephant (media)")));
    }

    @Test
    void testGetAnimalsV2Old() throws Exception {
        mockMvc.perform(get("/api/animals-old-media")
                        .header("Accept", "application/json;version=v2"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V2 Old: Lion Leo 2 years old, Elephant Ellie 10 years old (media)")));
    }

    @Test
    void testGetAnimalsDefaultOld() throws Exception {
        mockMvc.perform(get("/api/animals-old-media"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Default Old (media)")));
    }

    @Test
    void testDefaultDefaultVersionIsSetToV2() throws Exception {
        mockMvc.perform(get("/api/animals-media"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V2: Lion Leo 5 years old, Elephant Ellie 12 years old (media)")));
    }

    @Test
    void testGetAnimalsV1() throws Exception {
        mockMvc.perform(get("/api/animals-media")
                        .header("Accept", "application/json;version=v1"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1: Lion, Elephant (media)")));
    }

    @Test
    void testGetAnimalsV2() throws Exception {
        mockMvc.perform(get("/api/animals-media")
                        .header("Accept", "application/json;version=v2"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V2: Lion Leo 5 years old, Elephant Ellie 12 years old (media)")));
    }

    @Test
    void testGetAnimalsV1_1() throws Exception {
        mockMvc.perform(get("/api/animals-media")
                        .header("Accept", "application/json;version=v1.1"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1.1: Lion, Elephant - Africa (media)")));
    }

    @Test
    void testGetIndividualAnimalV1() throws Exception {
        mockMvc.perform(get("/api/animals-media/1")
                        .header("Accept", "application/json;version=v1"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1: Animal ID 1 Lion (media)")));
    }

    @Test
    void testPostAnimalV1() throws Exception {
        mockMvc.perform(post("/api/animals-media")
                        .header("Accept", "application/json;version=v1")
                        .content("Monkey")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1: Created animal: Monkey (media)")));
    }
    
    @Test
    void testPutAnimalV1() throws Exception {
        mockMvc.perform(put("/api/animals-media/1")
                        .header("Accept", "application/json;version=v1")
                        .content("Tiger")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1: Updated animal ID 1 to Tiger (media)")));
    }

    @Test
    void testPatchAnimalV1() throws Exception {
        mockMvc.perform(patch("/api/animals-media/1")
                        .header("Accept", "application/json;version=v1")
                        .content("Zebra")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1: Patched animal ID 1 with Zebra (media)")));
    }

    @Test
    void testDeleteAnimalV1() throws Exception {
        mockMvc.perform(delete("/api/animals-media/1")
                        .header("Accept", "application/json;version=v1"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1: Deleted animal ID 1 (media)")));
    }

    @Test
    void testUnsupportedVersion() throws Exception {
        mockMvc.perform(get("/api/animals-media")
                        .header("Accept", "application/json;version=v99"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testInvalidPath() throws Exception {
        mockMvc.perform(get("/animals-media/v1/api")
                        .header("Accept", "application/json;version=v2"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testAlternativeMediaType() throws Exception {
        mockMvc.perform(get("/api/animals-media")
                        .header("Accept", "application/xml;version=v2"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V2: Lion Leo 5 years old, Elephant Ellie 12 years old (media)")));
    }

    @Test
    void testCaseSensitivityMediaType() throws Exception {
        mockMvc.perform(get("/api/animals-media")
                        .header("Accept", "APPLICATION/JSON;VERSION=v1"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1: Lion, Elephant (media)")));
    }
}