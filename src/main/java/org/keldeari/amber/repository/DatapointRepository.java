package org.keldeari.amber.repository;

import org.keldeari.amber.model.entity.Datapoint;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DatapointRepository extends MongoRepository<Datapoint, String> {
}
