package org.keldeari.amber.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Event {
    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String displayName;
    private LocalDateTime startDate;
    private LocalDateTime endDate = null;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime deleteDate = null;
}
