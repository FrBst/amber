package org.keldeari.amber.model.request;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
@Valid
public class EventCreateDto {

    private LocalDateTime startDate;

    @NotNull
    private String name;
}
