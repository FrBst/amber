package org.keldeari.amber.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.Valid;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.keldeari.amber.model.core.FieldType;
import org.keldeari.amber.model.request.DatapointCreateDto;
import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.lang.NonNullFields;

@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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

    public Datapoint(String schemaId, String eventId, Node data) {
        this.schemaId = schemaId;
        this.eventId = eventId;
        this.data = data;

        LocalDateTime timestamp = LocalDateTime.now();
        this.createDate = timestamp;
        this.updateDate = timestamp;
    }

    public static Datapoint from(DatapointCreateDto dto) {
        return new Datapoint(dto.getSchemaId(), dto.getEventId(), dto.getData());
    }

    @Data
    @Valid
    public static class Node {
        private String fieldName;
        private String value;
        private FieldType type;
        private List<Node> children;
    }
}
