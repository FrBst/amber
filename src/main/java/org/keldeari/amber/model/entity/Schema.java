package org.keldeari.amber.model.entity;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import lombok.*;
import org.jetbrains.annotations.Nullable;
import org.keldeari.amber.model.core.FieldType;
import org.keldeari.amber.model.request.SchemaCreateDto;
import org.springframework.data.annotation.Id;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Schema {

    @Id
    @EqualsAndHashCode.Include
    @Nullable
    private String id;
    private String displayName;
    private Node data;

    public Schema(SchemaCreateDto dto) {
        this.displayName = dto.getName();
        this.data = new Node(dto.getData());
    }

    @Data
    @NoArgsConstructor
    public static class Node {
        private String name;
        private String description;
        private FieldType type;
        private List<Node> children;

        public Node(SchemaCreateDto.Node dto) {
            this.name = dto.getName();
            this.description = dto.getDescription();
            this.type = dto.getType();
            this.children = dto.getChildren().stream().map(Node::new).toList();
        }
    }
}

