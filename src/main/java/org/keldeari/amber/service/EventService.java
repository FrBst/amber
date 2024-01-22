package org.keldeari.amber.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.keldeari.amber.model.Event;
import org.keldeari.amber.repository.EventRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {
    
    private final EventRepository eventRepository;

    public List<Event> getEvents() {
        return eventRepository.findAll(Sort.by("startDate"));
    }

    public void createEvent(Event event) {
        event.setStartDate(LocalDateTime.now(ZoneOffset.UTC));
        eventRepository.insert(event);
    }

    @Transactional
    public void finishEvent(String eventId) {
        Event event = eventRepository.findById(eventId)
            .orElseThrow();

        event.setEndDate(LocalDateTime.now(ZoneOffset.UTC));
        eventRepository.save(event);
    }
}
