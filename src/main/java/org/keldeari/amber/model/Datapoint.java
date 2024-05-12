package org.keldeari.amber.model;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.Document;
import org.keldeari.amber.model.core.FieldType;
import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Datapoint {
    @Id
    @EqualsAndHashCode.Include
    private String id;
    private LocalDateTime deleteDate = null;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private String schemaId;
    private Node data;
    private String eventId;

    @Data
    public static class Node {
        private String fieldName;
        private String value;
        private FieldType type;
        private List<Node> children;
    }
}
