package org.keldeari.amber.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import java.time.LocalDateTime;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.keldeari.amber.configuration.Beans;
import org.keldeari.amber.exception.AmberException;
import org.keldeari.amber.model.Schema;
import org.keldeari.amber.model.request.DatapointCreateRequestDto.Node;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class BsonBuilderTest {

    BsonBuilder bsonBuilder = new BsonBuilder();
    ObjectMapper objectMapper = new Beans().yamlObjectMapper();

    private final String testDatapoint = """
            fieldName: None
            value: None
            children:
            - fieldName: stringField
              value: "Test String Field"
            - fieldName: dateField
              value: "2023-01-01T00:00:00"
            """;

    private final String testSchema = """
            id: testId
            displayName: Test Schema
            fields:
            - name: stringField
              displayName: String Field
              fieldType: STRING
            - name: dateField
              displayName: Date Field
              fieldType: DATETIMEUTC
            """;

    @Test
    void from_worksWithSimpleNode() throws JsonMappingException, JsonProcessingException {

        Node node = objectMapper.readValue(testDatapoint, Node.class);

        Schema schema = objectMapper.readValue(testSchema, Schema.class);

        Document doc = new BsonBuilder().from(node, schema);

        assertThat(doc.get("stringField", String.class)).isEqualTo("Test String Field");
        assertThat(doc.get("dateField", LocalDateTime.class))
                .isEqualTo(LocalDateTime.of(2023, 01, 01, 0, 0));
    }

    @Test
    void from_errorWhenWrongType() throws JsonMappingException, JsonProcessingException {

        Node node = objectMapper.readValue(testDatapoint, Node.class);

        Schema schema =
                objectMapper.readValue(testSchema.replace("DATETIMEUTC", "INTEGER"), Schema.class);

        assertThrowsExactly(AmberException.class, () -> new BsonBuilder().from(node, schema));
    }

    @Test
    void from_errorWhenFieldNotFound() throws JsonMappingException, JsonProcessingException {

        String datapoint = testDatapoint + "\n- fieldName: unknownField\n  value: 123";

        Node node = objectMapper.readValue(datapoint, Node.class);

        Schema schema = objectMapper.readValue(testSchema, Schema.class);

        assertThrowsExactly(IllegalArgumentException.class,
                () -> new BsonBuilder().from(node, schema));
    }

    @Test
    void from_worksWhenEmptyDatapoint() throws JsonMappingException, JsonProcessingException {

        String datapoint = """
                fieldName: None
                value: None
                """;

        Node node = objectMapper.readValue(datapoint, Node.class);

        Schema schema = objectMapper.readValue(testSchema, Schema.class);

        assertDoesNotThrow(() -> new BsonBuilder().from(node, schema));
    }

    @Test
    void from_worksWhenEmptySchema() throws JsonMappingException, JsonProcessingException {

        String datapoint = """
                fieldName: None
                value: None
                """;

        String schema = """
                id: testId
                displayName: Test Schema
                """;

        Node node = objectMapper.readValue(datapoint, Node.class);

        Schema schemaObject = objectMapper.readValue(schema, Schema.class);

        assertDoesNotThrow(() -> new BsonBuilder().from(node, schemaObject));
    }
}
