package org.keldeari.amber.service;

import org.keldeari.amber.model.Schema;
import org.keldeari.amber.repository.SchemaRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchemaService {
    
    private final SchemaRepository schemaRepository;
    
    public void createSchema(@NonNull Schema schema) {
        schemaRepository.insert(schema);
    }

    public Schema getSchema(@NonNull String schemaId) {
        return schemaRepository.findById(schemaId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Schema with specified id does not exist"));
    }
}
