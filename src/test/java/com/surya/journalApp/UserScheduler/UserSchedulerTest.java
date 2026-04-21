package com.surya.journalApp.UserScheduler;

import com.surya.journalApp.scheduler.UserScheduler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserSchedulerTest {

    @Autowired
    private UserScheduler userScheduler;

    @Test
    public void testFetchUserScheduler() {
        userScheduler.fetchUsersAndSendSaMail();
    }
}
