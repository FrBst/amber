package org.keldeari.amber.controller;

import java.util.List;

import org.keldeari.amber.model.entity.Schema;
import org.keldeari.amber.model.request.SchemaCreateDto;
import org.keldeari.amber.service.SchemaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/schemas")
@RequiredArgsConstructor
public class SchemaController {

    private final SchemaService schemaService;

    @GetMapping
    public List<Schema> getAllSchemas() {
        return schemaService.getAllSchemas();
    }

    @GetMapping("/{id}")
    public Schema getSchemaById(@PathVariable String id) {
        return schemaService.getSchema(id);
    }

    @PostMapping(value = "/create", consumes = "text/yaml")
    public void createSchema(@Valid @RequestBody SchemaCreateDto schema) {
        schemaService.createSchema(schema);
    }

}