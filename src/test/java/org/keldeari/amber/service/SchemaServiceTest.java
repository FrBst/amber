package org.keldeari.amber.service;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import org.junit.jupiter.api.Test;
import org.keldeari.amber.configuration.Beans;
import org.keldeari.amber.exception.AmberException;
import org.keldeari.amber.exception.IllegalFieldTypeException;
import org.keldeari.amber.model.Schema;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SchemaServiceTest {

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

    ObjectMapper objectMapper = new Beans().yamlObjectMapper();

    SchemaService schemaService = new SchemaService(null);

    @Test
    void from_errorWhenIllegalType() throws JsonMappingException, JsonProcessingException {

        Schema schema =
                objectMapper.readValue(testSchema.replace("DATETIMEUTC", "UNKNOWN"), Schema.class);

        assertThrowsExactly(IllegalFieldTypeException.class,
                () -> schemaService.validateSchema(schema));
    }
}
