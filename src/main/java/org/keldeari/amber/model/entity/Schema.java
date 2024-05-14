package org.keldeari.amber.model.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.keldeari.amber.model.core.FieldType;
import org.keldeari.amber.model.request.SchemaCreateDto;
import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Schema {

    @Id
    @EqualsAndHashCode.Include
    @Nullable
    private String id;
    private String displayName;
    private List<Node> fields;

    public Schema(String displayName, List<Node> fields) {
        this.displayName = displayName;
        this.fields = fields;
    }

    public static Schema from(SchemaCreateDto dto) {
        return new Schema(dto.getName(), dto.getFields());
    }

    @Data
    public static class Node {
        private String name;
        private String description;
        private FieldType type;
        private List<Node> children;
    }
}

