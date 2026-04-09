package com.surya.journalApp.Service;

import com.surya.journalApp.Entity.User;
import com.surya.journalApp.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    public User saveEntry(User user) {
           return userRepository.save(user);
    }

    public boolean saveNewUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("User"));
             userRepository.save(user);
             return true;

        }catch (Exception e){
          return false;
        }
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

    public User saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("User","Admin"));
        return userRepository.save(user);
    }

}
