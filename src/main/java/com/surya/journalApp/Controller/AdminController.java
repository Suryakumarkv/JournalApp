package com.surya.journalApp.Controller;

import com.surya.journalApp.Entity.User;
import com.surya.journalApp.Service.UserService;
import com.surya.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    public UserService userService;

    @Autowired
    private AppCache appCache;

    @GetMapping("/find-all")
    public ResponseEntity<?> getAllUsers() {
        List<User> all = userService.findAll();

        if(!all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-new-user")
    public void createUser(@RequestBody User user) {
        userService.saveAdmin(user);
    }

    @GetMapping("clear-cache")
    public void clearCache() {
        appCache.init();
    }
}
