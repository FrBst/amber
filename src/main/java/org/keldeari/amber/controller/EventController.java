package org.keldeari.amber.controller;

import java.util.List;

import org.keldeari.amber.model.Event;
import org.keldeari.amber.service.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/event")
public class EventController {
    
    private final EventService eventService;

    @PostMapping(consumes = "text/yaml")
    public void createEvent(@RequestBody Event event) {
        eventService.createEvent(event);
    }

    @PatchMapping("/{eventId}")
    public void finishEvent(@PathVariable String eventId) {
        eventService.finishEvent(eventId);
    }

    @GetMapping
    public List<Event> getEvents() {
        return eventService.getEvents();
    }
    
}
