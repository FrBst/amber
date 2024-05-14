package org.keldeari.amber.service;

import java.time.LocalDateTime;
import java.util.List;

import org.keldeari.amber.Utils;
import org.keldeari.amber.exception.AmberException;
import org.keldeari.amber.model.entity.Event;
import org.keldeari.amber.model.request.EventCreateDto;
import org.keldeari.amber.repository.EventRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {
    
    private final EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll(Sort.by("startDate"));
    }

    public List<Event> getRunningEvents() {
        return eventRepository.findRunningEvents();
    }

    public void createEvent(EventCreateDto request) {
        Event event = new Event();

        LocalDateTime now = Utils.now();
        event.setName(request.getName());
        event.setStartDate(Utils.coalesce(request.getStartDate(), now));
        event.setCreateDate(now);
        event.setUpdateDate(now);

        eventRepository.insert(event);
    }

    /**
     * Set event endDate as current date-time
     * @param eventId
     * @return true if endDate was set successfully. False if event is already finished
     * @throws AmberException when event with specified eventId is not found in the database
     */
    @Transactional
    public boolean finishEvent(String eventId) {
        Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new AmberException("Event does not exist"));

        if (event.getEndDate() != null) {
            return false;
        }

        LocalDateTime now = Utils.now();
        event.setEndDate(now);
        event.setUpdateDate(now);
        eventRepository.save(event);

        return true;
    }

    /**
     * Set event endDate as null
     * @param eventId
     * @return true if endDate was set successfully. False if event was not finished in the first place
     * @throws AmberException when event with specified eventId is not found in the database
     */
    public boolean unfinishEvent(String eventId) {
        Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new AmberException("Event does not exist"));

        if (event.getEndDate() == null) {
            return false;
        }

        LocalDateTime now = Utils.now();
        event.setEndDate(null);
        event.setUpdateDate(now);
        eventRepository.save(event);

        return true;
    }
}
