package org.keldeari.amber.repository;

import org.keldeari.amber.model.Schema;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SchemaRepository extends MongoRepository<Schema, String> {
    
}
