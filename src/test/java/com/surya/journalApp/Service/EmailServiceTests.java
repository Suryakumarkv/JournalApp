package com.surya.journalApp.Service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

//    @Disabled
    @Test
    void testSendMail() {
        emailService.sendEmail("suryakumarkv30@gmail.com",
                "Testing Java mail sender",
                "Hi, aap kaise hain ?");
    }
}