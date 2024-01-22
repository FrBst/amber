package org.keldeari.amber.model;

import java.time.LocalDateTime;

import org.bson.Document;
import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Datapoint {
    @Id
    @EqualsAndHashCode.Include
    private String id;
    private LocalDateTime deleteDate = null;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private Document data;
}
