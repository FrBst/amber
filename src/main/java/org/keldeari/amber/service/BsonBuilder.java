package org.keldeari.amber.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.bson.Document;
import org.keldeari.amber.exception.AmberException;
import org.keldeari.amber.exception.IllegalFieldTypeException;
import org.keldeari.amber.model.Schema;
import org.keldeari.amber.model.request.DatapointCreateRequestDto.Node;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BsonBuilder {

    /**
     * Ignores {@code root} and puts all the children (aka datapoint fields) in a new document
     * 
     * @param root
     * @return
     */
    public Document from(Node root, Schema schema) {
        return from(root.getChildren(), schema);
    }

    private Document from(List<Node> nodes, Schema schema) {
        Document res = new Document();

        nodes = nodes == null ? List.of() : nodes;

        Map<String, Schema.Field> fields = new HashMap<>();

        if (schema.getFields() != null) {
            fields = schema.getFields().stream()
                    .collect(Collectors.toMap(Schema.Field::getName, Function.identity()));
        }

        for (Node node : nodes) {
            res.append(node.getFieldName(), cast(node, fields.get(node.getFieldName())));
        }

        return res;
    }

    private Object cast(Node node, Schema.Field field) {

        if (field == null) {
            throw new IllegalArgumentException("Field not found");
        }

        try {
            switch (field.getFieldType()) {
                case STRING:
                    return node.getValue();
                case DATETIMEUTC:
                    DateTimeFormatter formatter =
                            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                    return LocalDateTime.parse(node.getValue(), formatter);
                default:
                    throw new IllegalFieldTypeException();
            }
        } catch (Exception e) {
            throw new AmberException(String.format("Cannot cast field %s to type %s",
                    node.getFieldName(), field.getFieldType()), e);
        }
    }
}
