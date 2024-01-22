package org.keldeari.amber.model.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EventCreateDto {
    @NotNull
    private String displayName;
    private LocalDateTime startDate;
}
