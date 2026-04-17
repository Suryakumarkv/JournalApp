package com.surya.journalApp.Controller;

import com.surya.journalApp.Entity.User;
import com.surya.journalApp.Repository.UserRepository;
import com.surya.journalApp.Service.UserService;
import com.surya.journalApp.Service.WeatherService;
import com.surya.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;


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
        userRepository.deleteByUserName(authentication.getName(), HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String greeting ="";
        WeatherResponse weatherResponse= weatherService.getWeather("Mumbai");
        if(weatherResponse!=null){
            greeting = ", weather feels like " + weatherResponse.getCurrent().getFeelslike();
        }

        return new ResponseEntity<>("Hi " + authentication.getName() + greeting , HttpStatus.OK);
    }
}
