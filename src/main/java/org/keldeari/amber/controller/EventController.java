package org.keldeari.amber.controller;

import java.util.List;

import org.keldeari.amber.model.Event;
import org.keldeari.amber.model.request.EventCreateDto;
import org.keldeari.amber.service.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/events")
public class EventController {
    
    private final EventService eventService;

    @PostMapping(value = "/create", consumes = "text/yaml")
    public void createEvent(@Valid @RequestBody EventCreateDto event) {
        eventService.createEvent(event);
    }

    @PatchMapping("/{eventId}/finish")
    public void finishEvent(@PathVariable String eventId) {
        eventService.finishEvent(eventId);
    }

    @PatchMapping("/{eventId}/unfinish")
    public void unfinishEvent(@PathVariable String eventId) {
        eventService.unfinishEvent(eventId);
    }

    @GetMapping("/all")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/running")
    public List<Event> getRunningEvents() {
        return eventService.getRunningEvents();
    }
    
}
