package com.surya.journalApp.Service;

import com.surya.journalApp.Entity.User;
import com.surya.journalApp.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTests {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Test
    void loadUserNameTests() {

        User mockUser = User.builder()
                .userName("ended")   // ✅ match input
                .password("65656565")
                .roles(new ArrayList<>())
                .build();

        when(userRepository.findByUserName("ended"))
                .thenReturn(mockUser);

        UserDetails userDetails =
                userDetailsServiceImpl.loadUserByUsername("ended");

        assertNotNull(userDetails);
        assertEquals("ended", userDetails.getUsername()); // ✅ strong assertion
    }
}