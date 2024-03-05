package org.keldeari.amber.controller;

import org.keldeari.amber.model.request.DatapointCreateRequestDto;
import org.keldeari.amber.service.DatapointService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RestController
public class DatapointController {

    private final DatapointService datapointService;

    @PostMapping(value = "/datapoint", consumes = {"text/yaml"})
    public void createDatapoint(@RequestBody DatapointCreateRequestDto datapoint) {
        datapointService.createDatapoint(datapoint);
    }
}
