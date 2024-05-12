package org.keldeari.amber.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.apache.commons.lang3.NotImplementedException;
import org.keldeari.amber.model.Datapoint;
import org.keldeari.amber.model.Schema;
import org.keldeari.amber.model.request.DatapointCreateRequestDto;
import org.keldeari.amber.repository.DatapointRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class DatapointService {

    private final DatapointRepository datapointRepository;

    @Qualifier("yamlObjectMapper")
    private final ObjectMapper objectMapper;

    private final SchemaService schemaService;

    public void createDatapoint(DatapointCreateRequestDto request) {

        Schema schema = schemaService.getSchema(request.getSchemaId());

        if (!isValid(request.getData(), schema)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Schema and datapoint mismatch");
        }

        Datapoint datapoint = new Datapoint();

        LocalDateTime createDate = LocalDateTime.now(ZoneOffset.UTC);
        datapoint.setCreateDate(createDate);
        datapoint.setUpdateDate(createDate);
        datapoint.setData(request.getData());

        datapointRepository.insert(datapoint);
    }

    private boolean isValid(Datapoint.Node data, Schema schema) {
        throw new NotImplementedException();
    }
}
