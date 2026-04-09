package com.surya.journalApp.Controller;

import com.surya.journalApp.Entity.User;
import com.surya.journalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class publicController {

    @Autowired
    UserService userService;

    @PostMapping("/create-user")
    public boolean addUser(@RequestBody User user) {
        return userService.saveNewUser(user);
    }

}
