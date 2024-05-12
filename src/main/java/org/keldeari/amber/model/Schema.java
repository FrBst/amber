package org.keldeari.amber.model;

import java.util.List;

import org.keldeari.amber.model.core.FieldType;
import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Schema {

    @Id
    private String id;
    private String displayName;
    private List<Field> fields;

    @Data
    public static class Field {

        private String name;
        private String displayName;
        private String description;
        private String fieldType;
    }


    @Data
    public static class Node {
        private String name;
        private FieldType type;
        private List<Node> children;
    }
}

