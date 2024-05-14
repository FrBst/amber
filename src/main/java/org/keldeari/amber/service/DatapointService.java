package org.keldeari.amber.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

        if (!isValid(dto.getData(), schema)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Schema and datapoint mismatch");
        }

        Datapoint datapoint = Datapoint.from(dto);

        datapointRepository.insert(datapoint);
    }

    private boolean isValid(Datapoint.Node data, Schema schema) {
        return true;
    }
}
