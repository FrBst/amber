package org.keldeari.amber.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.keldeari.amber.model.entity.Schema;
import org.keldeari.amber.model.core.FieldType;

import java.util.List;

@Data
public class SchemaCreateDto {

    @NotNull
    private String name;

    @NotNull
    @Valid
    private Node data;

    @Data
    public static class Node {

        @NotNull
        private String name;

        @NotNull
        private String description;

        @NotNull
        private FieldType type;

        @NotNull
        @Valid
        private List<Node> children;
    }
}
