package org.keldeari.amber.repository;

import org.keldeari.amber.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {
    
}
