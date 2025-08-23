package com.demo.apiversioningtest;

import com.demo.apiversioningtest.controller.BasicTestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BasicTestController.class)
public class BasicTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testBasic() throws Exception {
        mockMvc.perform(get("/test/basic"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Basic test")));
    }
}
