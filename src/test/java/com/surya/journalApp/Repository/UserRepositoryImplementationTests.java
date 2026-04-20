package com.surya.journalApp.Repository;

import com.surya.journalApp.Repository.UserRepositoryImplementation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryImplementationTests {

    @Autowired
    private UserRepositoryImplementation userRepository;

    @Test
    public void testGetUsersForSA() {
        var users = userRepository.getUsersForSA();

        System.out.println(users);

        // Optional assertion
        // assertNotNull(users);
    }
}