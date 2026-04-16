package com.surya.journalApp.Service;

import com.surya.journalApp.Entity.User;
import com.surya.journalApp.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;

@SpringBootTest
 class UserDetailsServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();   // 🔥 IMPORTANT
    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
     void testSaveUserDetails(User user) {
        assertTrue(userService.saveNewUser(user));
    }
}