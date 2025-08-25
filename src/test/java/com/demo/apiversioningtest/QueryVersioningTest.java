package com.demo.apiversioningtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class QueryVersioningTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAnimalsV1Old() throws Exception {
        mockMvc.perform(get("/api/animals-old-query?version=v1"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1 Old: Lion, Elephant (query)")));
    }

    @Test
    void testGetAnimalsV2Old() throws Exception {
        mockMvc.perform(get("/api/animals-old-query?version=v2"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V2 Old: Lion Leo 2 years old, Elephant Ellie 10 years old (query)")));
    }

    @Test
    void testGetAnimalsDefaultOld() throws Exception {
        mockMvc.perform(get("/api/animals-old-query"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Default Old (query)")));
    }

    // Should return 400 because configurer.setVersionRequired(true);
    @Test
    void testDefault() throws Exception {
        mockMvc.perform(get("/api/animals-query"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testGetAnimalsV1() throws Exception {
        mockMvc.perform(get("/api/animals-query?version=v1"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1: Lion, Elephant (query)")));
    }

    @Test
    void testGetAnimalsV2() throws Exception {
        mockMvc.perform(get("/api/animals-query?version=v2"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V2: Lion Leo 2 years old, Elephant Ellie 10 years old (query)")));
    }

    // Should return 400 because v3 is not in configurer.addSupportedVersions("v1", "v2", "v1.1");
    @Test
    void testGetAnimalsV3() throws Exception {
        mockMvc.perform(get("/api/animals-query?version=v3"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testGetAnimalsV1_1() throws Exception {
        mockMvc.perform(get("/api/animals-query?version=v1.1"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1.1: Lion, Elephant - Africa (query)")));
    }

    @Test
    void testGetIndividualAnimalV1() throws Exception {
        mockMvc.perform(get("/api/animals-query/1?version=v1"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1: Animal ID 1 Lion (query)")));
    }

    @Test
    void testPostAnimalV1() throws Exception {
        mockMvc.perform(post("/api/animals-query?version=v1")
                        .content("Monkey")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1: Created animal: Monkey (query)")));
    }

    @Test
    void testPutAnimalV1() throws Exception {
        mockMvc.perform(put("/api/animals-query/1?version=v1")
                        .content("Tiger")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1: Updated animal ID 1 to Tiger (query)")));
    }

    @Test
    void testPatchAnimalV1() throws Exception {
        mockMvc.perform(patch("/api/animals-query/1?version=v1")
                        .content("Zebra")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1: Patched animal ID 1 with Zebra (query)")));
    }

    @Test
    void testDeleteAnimalV1() throws Exception {
        mockMvc.perform(delete("/api/animals-query/1?version=v1"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("V1: Deleted animal ID 1 (query)")));
    }


    @Test
    void testUnsupportedVersion() throws Exception {
        mockMvc.perform(get("/api/animals-query?version=v99"))
                .andExpect(status().is4xxClientError());
    }
}
