package com.surya.journalApp.Controller;

import com.surya.journalApp.Entity.User;
import com.surya.journalApp.Repository.UserRepository;
import com.surya.journalApp.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {

        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();

        User userIndb =userService.findByUsername(username);

        if(userIndb!=null){
             userIndb.setUserName(user.getUserName());
             userIndb.setPassword(user.getPassword());
             userService.saveNewUser(userIndb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping
    public ResponseEntity<?> deleteById() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
