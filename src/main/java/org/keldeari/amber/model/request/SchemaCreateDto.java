package org.keldeari.amber.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.keldeari.amber.model.entity.Schema;
import org.keldeari.amber.model.core.FieldType;

import java.util.List;

@Data
@Valid
public class SchemaCreateDto {

    @NotNull
    private String name;
    @NotNull
    private List<Schema.Node> fields;
}
