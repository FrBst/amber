package org.keldeari.amber.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.keldeari.amber.model.Schema.Field.FieldType;
import org.keldeari.amber.model.request.DatapointCreateRequestDto.Node;

class BsonBuilderTest {

    @Test
    void from_worksWithSimpleNode() {
        Node date = new Node();
        date.setDisplayName("Test Date Field");
        date.setName("testDate");
        date.setFieldType(FieldType.DATETIMEUTC);
        date.setValue("2023-01-01T00:00:00");
        
        Node string = new Node();
        string.setName("testString");
        string.setFieldType(FieldType.STRING);
        string.setValue("Test String Field");

        Node node = new Node();
        node.setName("schema");
        node.setFieldType(FieldType.STRING);
        node.setValue("12345");
        node.setChildren(List.of(date, string));


        Document doc = new BsonBuilder().from(node);

        assertThat(doc.get("testString", String.class))
            .isEqualTo("Test String Field");
        assertThat(doc.get("testDate", LocalDateTime.class))
            .isEqualTo(LocalDateTime.of(2023, 01, 01, 0, 0));
    }
}
