package org.keldeari.amber.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.keldeari.amber.model.entity.Datapoint;

@Data
@Valid
public class DatapointCreateDto {
    @NotNull
    private String schemaId;
    private String eventId;
    @NotNull
    private Datapoint.Node data;
}
