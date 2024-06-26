package org.keldeari.amber.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SchemaControllerTest {

    private final String testSchema = """
            name: pressure
            data:
              name: root
              description: "Root node"
              type: UNKNOWN
              children: []
            """;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createSchema_errorWhenIllegalDatatype() throws Exception {
        mockMvc.perform(post("/schemas/create").contentType("text/yaml").content(testSchema))
                .andExpect(status().is4xxClientError());
    }
}
