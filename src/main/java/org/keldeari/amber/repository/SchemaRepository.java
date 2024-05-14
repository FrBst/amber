package org.keldeari.amber.repository;

import org.keldeari.amber.model.entity.Schema;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SchemaRepository extends MongoRepository<Schema, String> {
    
}
