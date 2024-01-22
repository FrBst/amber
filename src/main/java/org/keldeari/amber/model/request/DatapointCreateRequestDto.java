package org.keldeari.amber.model.request;

import java.util.List;

import org.keldeari.amber.model.Schema.Field.FieldType;

import lombok.Data;

@Data
public class DatapointCreateRequestDto {
    private String schemaId;
    private Node data;

    @Data
    public static class Node {
        private String name;
        private String displayName;
        private String value;
        private List<Node> children;
        private FieldType fieldType;
    }
}
