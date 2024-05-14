package org.keldeari.amber.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.keldeari.amber.model.core.FieldType;
import org.keldeari.amber.model.entity.Datapoint;

import java.util.List;

@Data
public class DatapointCreateDto {

    @NotNull
    private String schemaId;

    private String eventId;

    @NotNull
    @Valid
    private Node data;

    @Data
    public static class Node {

        @NotNull
        private String fieldName;

        @NotNull
        private String value;

        @NotNull
        private FieldType type;

        @NotNull
        @Valid
        private List<Node> children;
    }
}
