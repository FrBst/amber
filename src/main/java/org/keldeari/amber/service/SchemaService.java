package org.keldeari.amber.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.keldeari.amber.exception.SchemaValidationException;
import org.keldeari.amber.model.core.FieldType;
import org.keldeari.amber.model.entity.Schema;
import org.keldeari.amber.model.request.SchemaCreateDto;
import org.keldeari.amber.repository.SchemaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchemaService {

    private final SchemaRepository schemaRepository;

    public void createSchema(@NonNull SchemaCreateDto dto) {
        Schema schema = new Schema(dto);
        validate(schema);
        schemaRepository.save(schema);
    }

    public Schema getSchema(@NonNull String schemaId) {
        return schemaRepository.findById(schemaId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Schema with specified id does not exist"));
    }

    public List<Schema> getAllSchemas() {
        return schemaRepository.findAll();
    }

    private void validate(Schema schema) {
        if (schema.getData().getType() != FieldType.ROOT) {
            throw new SchemaValidationException("Root node must be of type ROOT");
        }

        validate(schema.getData());
    }

    private void validate(Schema.Node node) {

        boolean canHaveChildren = node.getType() == FieldType.NODE || node.getType() == FieldType.ROOT;

        if (!canHaveChildren && !node.getChildren().isEmpty()) {
            throw new SchemaValidationException(String.format("Node %s has children, must also have type NODE", node.getName()));
        }

        for (Schema.Node child : node.getChildren()) {
            validate(child);
        }
    }
}
