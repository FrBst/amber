package org.keldeari.amber.controller;

import org.keldeari.amber.model.Datapoint;
import org.keldeari.amber.model.Schema;
import org.keldeari.amber.model.request.DatapointCreateRequestDto;
import org.keldeari.amber.service.DatapointService;
import org.keldeari.amber.service.SchemaService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {
    
    private final SchemaService schemaService;
    private final DatapointService datapointService;

    @PostMapping("/schema")
    public void createSchema(@RequestBody Schema schema) {
        schemaService.createSchema(schema);
    }

    @PostMapping("/datapoint")
    public void createDatapoint(@RequestBody DatapointCreateRequestDto datapoint) {
        try {
            datapointService.createDatapoint(datapoint);
        } catch (JsonProcessingException e) {
            log.debug("Failed datapoint parsing, exception {}, datapoint: {}", e.getMessage(), datapoint);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/schema")
    public Schema getSchema(String id) {
        return schemaService.getSchema(id);
    }
}
