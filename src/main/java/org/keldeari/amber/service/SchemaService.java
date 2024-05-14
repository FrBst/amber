package org.keldeari.amber.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.keldeari.amber.exception.IllegalFieldTypeException;
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
        Schema schema = Schema.from(dto);
        isValid(schema);
        schemaRepository.save(schema);
    }

    public Schema getSchema(@NonNull String schemaId) {
        return schemaRepository.findById(schemaId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Schema with specified id does not exist"));
    }

    public List<Schema> getAllSchemas() {
        return schemaRepository.findAll();
    }

    private boolean isValid(Schema schema) {
        for (Schema.Node node : schema.getFields()) {
            if (!isValid(node)) {
                return false;
            }


//            for (Schema.Field field : schema.getFields()) {
//                try {
//                    FieldType.valueOf(field.getFieldType());
//                } catch (IllegalArgumentException e) {
//                    throw new IllegalFieldTypeException(
//                            String.format("Illegal field type %s", field.getFieldType()));
//                }
//            }
        }
        return true;
    }

    private boolean isValid(Schema.Node node) {
        return true;
    }
}
