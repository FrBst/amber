package org.keldeari.amber.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Datapoint {

    @Id
    private String id;
    private String schemaId;
    private String data;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime deleteDate;
    private boolean isDeleted;
}
