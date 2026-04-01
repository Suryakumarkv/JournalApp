package com.surya.journalApp.Controller;

import com.surya.journalApp.Entity.User;
import com.surya.journalApp.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.saveEntry(user);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String userName) {
        User userIndb =userService.findByUsername(userName);

        if(userIndb!=null){
             userIndb.setUserName(user.getUserName());
             userIndb.setPassword(user.getPassword());
             userService.saveEntry(userIndb);
        }
        return ResponseEntity.ok().build();
    }

}
