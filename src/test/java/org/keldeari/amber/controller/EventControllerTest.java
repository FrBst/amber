package org.keldeari.amber.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.keldeari.amber.Utils;
import org.keldeari.amber.model.request.EventCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class EventControllerTest {

    @Container
    static MongoDBContainer mongoDBContainer =
            new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("yamlObjectMapper")
    private ObjectMapper yamlObjectMapper;

    @Test
    void createEvent_ValidationFails() throws Exception {
        EventCreateDto request = new EventCreateDto();
        request.setStartDate(Utils.now());
        request.setName(null);

        mockMvc.perform(post("/events/create").contentType("text/yaml").content(asString(request)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void createEvent_ValidationSuccess() throws Exception {
        EventCreateDto request = new EventCreateDto();
        request.setName("test");
        mockMvc.perform(post("/events/create").contentType("text/yaml").content(asString(request)))
                .andExpect(status().is2xxSuccessful());
    }

    public String asString(final Object obj) {
        try {
            return yamlObjectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
