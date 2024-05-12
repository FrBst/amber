package org.keldeari.amber.model.request;

import lombok.Data;
import org.keldeari.amber.model.Datapoint;

@Data
public class DatapointCreateRequestDto {
    private String schemaId;
    private Datapoint.Node data;
    private String eventId;
}
