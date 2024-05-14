package org.keldeari.amber.model.entity;

import java.time.LocalDateTime;

import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Event {
    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String name;
    private LocalDateTime startDate;

    @Nullable
    private LocalDateTime endDate;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @Nullable
    private LocalDateTime deleteDate;
}
