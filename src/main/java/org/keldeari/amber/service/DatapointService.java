package org.keldeari.amber.service;

import org.keldeari.amber.model.Datapoint;
import org.keldeari.amber.model.Schema;
import org.keldeari.amber.repository.DatapointRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DatapointService {
    
    private final DatapointRepository datapointRepository;

    private final SchemaService schemaService;

    
    public void createDatapoint(Datapoint datapoint) {
        Schema schema = schemaService.getSchema(datapoint.getSchemaId());

        datapointRepository.save(datapoint);
    }
}
