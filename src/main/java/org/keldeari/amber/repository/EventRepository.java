package org.keldeari.amber.repository;

import java.util.List;

import org.keldeari.amber.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface EventRepository extends MongoRepository<Event, String> {
    
    @Query("{ 'endDate' : null }")
    List<Event> findRunningEvents();
}
