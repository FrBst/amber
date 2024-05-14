package org.keldeari.amber.controller;

import jakarta.validation.Valid;
import org.keldeari.amber.model.request.DatapointCreateDto;
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
    public void createDatapoint(@Valid @RequestBody DatapointCreateDto datapoint) {
        datapointService.createDatapoint(datapoint);
    }
}
