package org.keldeari.amber.repository;

import org.keldeari.amber.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    
}
