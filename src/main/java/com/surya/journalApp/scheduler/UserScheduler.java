package com.surya.journalApp.scheduler;

import com.surya.journalApp.Entity.JournalEntry;
import com.surya.journalApp.Entity.User;
import com.surya.journalApp.Repository.UserRepositoryImplementation;
import com.surya.journalApp.Service.EmailService;
import com.surya.journalApp.Service.SentimentAnalysisService;
import com.surya.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
     private EmailService emailService;

    @Autowired
    private UserRepositoryImplementation userRepositoryImplementation;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
   private AppCache  appCache;


// @Scheduled(cron = "*/10 * * * * *") // every 10 seconds
    @Scheduled(cron = "0 0 9  * * SUN")
    public void fetchUserAndSendMail() {
          List<User> users = userRepositoryImplementation.getUsersForSA();

          for(User user : users){
              List<JournalEntry> journalEntries = user.getJournalEntries();
              List<String> filteredList =  journalEntries.stream()
                      .filter(x -> x.getDate()
                              .isAfter(LocalDateTime.now()
                                      .minus(7, ChronoUnit.DAYS)))
                      .map(x -> x.getContent())
                      .collect(Collectors.toList());

                  String entry = String.join("\n", filteredList);
                  String sentiment = sentimentAnalysisService.getSentiment(entry);
                 emailService.sendEmail(user.getEmail(), "sentimen for last 7 days", sentiment);
          }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache() {
        appCache.init();
    }

}
