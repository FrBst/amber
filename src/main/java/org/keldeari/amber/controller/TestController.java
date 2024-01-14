package org.keldeari.amber.controller;

import org.keldeari.amber.model.Datapoint;
import org.keldeari.amber.model.Schema;
import org.keldeari.amber.service.DatapointService;
import org.keldeari.amber.service.SchemaService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestController {
    
    private final SchemaService schemaService;
    private final DatapointService datapointService;

    @PostMapping("/schema")
    public void createSchema(@RequestBody Schema schema) {
        schemaService.createSchema(schema);
    }

    @PostMapping("/datapoint")
    public void createDatapoint(@RequestBody Datapoint datapoint) {
        datapointService.createDatapoint(datapoint);
    }

    @GetMapping("/schema")
    public Schema getSchema(String id) {
        return schemaService.getSchema(id);
    }
}
