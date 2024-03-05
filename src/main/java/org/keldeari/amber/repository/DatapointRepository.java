package org.keldeari.amber.repository;

import org.keldeari.amber.model.Datapoint;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DatapointRepository extends MongoRepository<Datapoint, String> {
}
