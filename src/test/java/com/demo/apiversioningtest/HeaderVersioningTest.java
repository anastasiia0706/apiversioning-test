package com.demo.apiversioningtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class HeaderVersioningTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAnimalsV1Old() throws Exception {
        mockMvc.perform(get("/api/animals-old-header")
                        .header("X-API-Version", "v1"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1 Old: Lion, Elephant (header)")));
    }

    @Test
    void testGetAnimalsV2Old() throws Exception {
        mockMvc.perform(get("/api/animals-old-header")
                        .header("X-API-Version", "v2"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V2 Old: Lion Leo 2 years old, Elephant Ellie 10 years old (header)")));
    }

    @Test
    void testGetAnimalsDefaultOld() throws Exception {
        mockMvc.perform(get("/api/animals-old-header"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Default Old (header)")));
    }

    @Test
    void testDefaultDefaultVersionIsSetToV2() throws Exception {
        mockMvc.perform(get("/api/animals-header"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V2: Lion Leo 5 years old, Elephant Ellie 12 years old (header)")));
    }

    @Test
    void testGetAnimalsV1() throws Exception {
        mockMvc.perform(get("/api/animals-header")
                        .header("X-API-Version", "v1"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1: Lion, Elephant (header)")));
    }

    @Test
    void testGetAnimalsV2() throws Exception {
        mockMvc.perform(get("/api/animals-header")
                        .header("X-API-Version", "v2"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V2: Lion Leo 5 years old, Elephant Ellie 12 years old (header)")));
    }

    @Test
    void testGetAnimalsV1_1() throws Exception {
        mockMvc.perform(get("/api/animals-header")
                        .header("X-API-Version", "v1.1"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1.1: Lion, Elephant - Africa (header)")));
    }

    @Test
    void testGetIndividualAnimalV1() throws Exception {
        mockMvc.perform(get("/api/animals-header/1")
                        .header("X-API-Version", "v1"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1: Animal ID 1 Lion (header)")));
    }

    @Test
    void testPostAnimalV1() throws Exception {
        mockMvc.perform(post("/api/animals-header")
                        .header("X-API-Version", "v1")
                        .content("Monkey")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1: Created animal: Monkey (header)")));
    }


    @Test
    void testPutAnimalV1() throws Exception {
        mockMvc.perform(put("/api/animals-header/1")
                        .header("X-API-Version", "v1")
                        .content("Tiger")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1: Updated animal ID 1 to Tiger (header)")));
    }

    @Test
    void testPatchAnimalV1() throws Exception {
        mockMvc.perform(patch("/api/animals-header/1")
                        .header("X-API-Version", "v1")
                        .content("Zebra")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1: Patched animal ID 1 with Zebra (header)")));
    }

    @Test
    void testDeleteAnimalV1() throws Exception {
        mockMvc.perform(delete("/api/animals-header/1")
                        .header("X-API-Version", "v1"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1: Deleted animal ID 1 (header)")));
    }

    @Test
    void testUnsupportedVersion() throws Exception {
        mockMvc.perform(get("/api/animals-header")
                        .header("X-API-Version", "v99"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testInvalidPath() throws Exception {
        mockMvc.perform(get("/animals-header/v1/api")
                        .header("X-API-Version", "v2"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testAlternativeHeaderName() throws Exception {
        mockMvc.perform(get("/api/animals-header")
                        .header("API-Version", "v2"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V2: Lion Leo 5 years old, Elephant Ellie 12 years old (header)")));
    }

    @Test
    void testCaseSensitivityHeader() throws Exception {
        mockMvc.perform(get("/api/animals-header")
                        .header("x-api-version", "v1"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1: Lion, Elephant (header)")));
    }

}
