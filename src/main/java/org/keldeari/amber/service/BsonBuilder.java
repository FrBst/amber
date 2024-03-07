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
import org.keldeari.amber.model.core.FieldType;
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

    /**
     * Puts all the nodes from the list in a new document, using the schema to cast the values
     * 
     * @param nodes
     * @return
     */
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

    /**
     * Tries to cast the value of the node to the type of the field
     * 
     * @param node
     * @param field
     * @return
     * @throws AmberException when the cast fails
     * @throws IllegalFieldTypeException when the field type is not supported
     * @throws IllegalArgumentException when the field is not found in the schema
     */
    private Object cast(Node node, Schema.Field field) {

        if (field == null) {
            throw new IllegalArgumentException("Field not found");
        }

        try {
            return cast(node.getValue(), field);
        } catch (IllegalArgumentException e) {
            throw new AmberException(
                    String.format("Field type %s is not supported", field.getFieldType()));
        } catch (AmberException e) {
            throw new AmberException(String.format("Error casting value of field %s to type %s",
                    node.getFieldName(), field.getFieldType()));
        }
    }

    private Object cast(String value, Schema.Field field) {
        FieldType fieldType = FieldType.valueOf(field.getFieldType());

        try {
            switch (fieldType) {
                case STRING:
                    return value;
                case DATETIMEUTC:
                    DateTimeFormatter formatter =
                            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                    return LocalDateTime.parse(value, formatter);
                default:
                    throw new IllegalArgumentException();
            }
        } catch (AmberException e) {
            throw e;
        } catch (Exception e) {
            throw new AmberException(e);
        }
    }
}
