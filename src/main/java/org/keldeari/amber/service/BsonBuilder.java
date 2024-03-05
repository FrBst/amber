package org.keldeari.amber.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.bson.Document;
import org.keldeari.amber.exception.IllegalFieldTypeException;
import org.keldeari.amber.model.request.DatapointCreateRequestDto.Node;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BsonBuilder {


    public Document from(Node node) {
        Document res = new Document();

        if (node.getChildren() == null) {
            return res;
        }

        for (Node child : node.getChildren()) {
            res.append(child.getName(), cast(child));
        }

        return res;
    }
    
    private Object cast(Node node) {
        switch (node.getFieldType()) {
            case STRING:
                return node.getValue();
            case DATETIMEUTC:
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                return LocalDateTime.parse(node.getValue(), formatter);
            case NODE:
                return from(node);
            default:
                throw new IllegalFieldTypeException();
        }
    }
}
