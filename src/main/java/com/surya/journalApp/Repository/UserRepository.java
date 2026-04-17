package com.surya.journalApp.Repository;

import com.surya.journalApp.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    public User findByUserName(String username);

    void deleteByUserName(String name, HttpStatus ok);
}
