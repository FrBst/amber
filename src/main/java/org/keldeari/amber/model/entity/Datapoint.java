package org.keldeari.amber.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.Valid;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.keldeari.amber.model.core.FieldType;
import org.keldeari.amber.model.request.DatapointCreateDto;
import org.springframework.data.annotation.Id;

import org.springframework.lang.NonNullFields;

@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Datapoint {

    @Id
    @EqualsAndHashCode.Include
    @Nullable
    private String id;

    @Nullable
    private LocalDateTime deleteDate;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private String schemaId;
    private Node data;

    @Nullable
    private String eventId;

    public Datapoint(DatapointCreateDto dto) {

        this.schemaId = dto.getSchemaId();
        this.eventId = dto.getEventId();
        this.data = new Node(dto.getData());

        LocalDateTime timestamp = LocalDateTime.now();
        this.createDate = timestamp;
        this.updateDate = timestamp;
    }

    @Data
    @NoArgsConstructor
    public static class Node {
        private String fieldName;
        private String value;
        private FieldType type;
        private List<Node> children;

        public Node(DatapointCreateDto.Node dto) {
            this.fieldName = dto.getFieldName();
            this.value = dto.getValue();
            this.type = dto.getType();
            this.children = dto.getChildren().stream().map(Node::new).toList();
        }
    }
}
