package com.surya.journalApp.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    public void testSendMail() {
        emailService.sendEmail("suryakumarkv04@gmail.com","Testing Java mail sender","Hi, aap kaise hain? mummy kaise hai?");
    }
}
