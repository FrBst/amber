package org.keldeari.amber.model.request;

import java.util.List;
import lombok.Data;

@Data
public class DatapointCreateRequestDto {
    private String schemaId;
    private Node data;
    private String eventId;

    @Data
    public static class Node {
        private String fieldName;
        private String value;
        private List<Node> children;
    }
}
