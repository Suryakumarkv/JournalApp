package com.surya.journalApp.Service;

import com.surya.journalApp.Entity.JournalEntry;
import com.surya.journalApp.Entity.User;
import com.surya.journalApp.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;



    public User saveEntry(User user) {
        try {
           return userRepository.save(user);
        }catch (Exception e){
            log.error("Exception", e);
        }
        return null;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
    }
}
