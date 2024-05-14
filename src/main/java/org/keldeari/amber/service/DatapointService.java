package org.keldeari.amber.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keldeari.amber.exception.DatapointValidationException;
import org.keldeari.amber.model.entity.Datapoint;
import org.keldeari.amber.model.entity.Schema;
import org.keldeari.amber.model.request.DatapointCreateDto;
import org.keldeari.amber.repository.DatapointRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class DatapointService {

    private final DatapointRepository datapointRepository;

    @Qualifier("yamlObjectMapper")
    private final ObjectMapper objectMapper;

    private final SchemaService schemaService;

    public void createDatapoint(DatapointCreateDto dto) {

        Schema schema = schemaService.getSchema(dto.getSchemaId());
        Datapoint datapoint = new Datapoint(dto);

        validate(datapoint.getData(), schema.getData());

        datapointRepository.insert(datapoint);
    }

    private void validate(Datapoint.Node dataNode, Schema.Node schemaNode) {
        if (dataNode.getType() != schemaNode.getType()) {
            throw new DatapointValidationException(String.format("Schema mismatch for node '%s'",
                    dataNode.getFieldName()));
        }

        if (!dataNode.getFieldName().equals(schemaNode.getName())) {
            throw new DatapointValidationException(String.format("Field name mismatch with schema for node '%s'",
                    dataNode.getFieldName()));
        }

        if (dataNode.getChildren().size() != schemaNode.getChildren().size()) {
            throw new DatapointValidationException(
                    String.format("Different number of children with schema for node '%s'",dataNode.getFieldName()));
        }

        for (int i = 0; i < dataNode.getChildren().size(); i++) {
            validate(dataNode.getChildren().get(i), schemaNode.getChildren().get(i));
        }
    }
}
