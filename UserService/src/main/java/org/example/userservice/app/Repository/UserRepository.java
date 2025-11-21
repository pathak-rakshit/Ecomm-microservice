package org.example.userservice.app.Repository;

import org.example.userservice.app.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {
    
    public User findById(String id);

    void deleteById(String id);
}
