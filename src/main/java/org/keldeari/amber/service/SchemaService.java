package org.keldeari.amber.service;

import java.util.List;
import org.keldeari.amber.exception.IllegalFieldTypeException;
import org.keldeari.amber.model.Schema;
import org.keldeari.amber.model.core.FieldType;
import org.keldeari.amber.repository.SchemaRepository;
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
        validateSchema(schema);
        schemaRepository.insert(schema);
    }

    public Schema getSchema(@NonNull String schemaId) {
        return schemaRepository.findById(schemaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Schema with specified id does not exist"));
    }

    public List<Schema> getAllSchemas() {
        return schemaRepository.findAll();
    }

    public void validateSchema(Schema schema) {
        if (schema.getFields() == null) {
            return;
        }

        for (Schema.Field field : schema.getFields()) {
            try {
                FieldType.valueOf(field.getFieldType());
            } catch (IllegalArgumentException e) {
                throw new IllegalFieldTypeException(
                        String.format("Illegal field type %s", field.getFieldType()));
            }
        }
    }
}
