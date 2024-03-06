package org.keldeari.amber.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.keldeari.amber.model.Datapoint;
import org.keldeari.amber.model.Schema;
import org.keldeari.amber.model.request.DatapointCreateRequestDto;
import org.keldeari.amber.repository.DatapointRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DatapointService {
    private final DatapointRepository datapointRepository;
    private final BsonBuilder bsonBuilder;

    private final SchemaService schemaService;

    public void createDatapoint(DatapointCreateRequestDto request) {

        Schema schema = schemaService.getSchema(request.getSchemaId());

        Datapoint datapoint = new Datapoint();

        LocalDateTime createDate = LocalDateTime.now(ZoneOffset.UTC);
        datapoint.setCreateDate(createDate);
        datapoint.setUpdateDate(createDate);

        datapoint.setData(bsonBuilder.from(request.getData(), schema));

        datapointRepository.insert(datapoint);
    }
}
