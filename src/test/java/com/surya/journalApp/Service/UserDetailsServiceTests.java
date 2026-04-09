package com.surya.journalApp.Service;

import com.surya.journalApp.Entity.User;
import com.surya.journalApp.Repository.UserRepository;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.CsvSources;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserDetailsServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveUserDetails(User user) {
         assertTrue(userService.saveNewUser(user));
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {
            "poorva",
            "poornima",
            "suryaKumarK"
    })
    public void testFindByUsername(String name) {
        assertNotNull(userRepository.findByUserName(name));
        assertEquals(4, 2 + 2);
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "2,2,4",
            "4,7,11",
            "1,2,4"
    })
    public void test(int a, int b, int expected) {
        assertEquals(expected, a + b);
    }

}
