package com.surya.journalApp.Service;

import com.surya.journalApp.Entity.JournalEntry;
import com.surya.journalApp.Entity.User;
import com.surya.journalApp.Repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class JournalEntryService {

    @Autowired
   private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;


    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUsername(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);

            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
            System.out.println("Saved to MongoDB");
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("Error saving JournalEntry");
        }
    }

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> findAll() {
        return journalEntryRepository.findAll();
    }

    public JournalEntry findById(ObjectId id) {
        return journalEntryRepository.findById(id).get();
    }

    public void deleteById(ObjectId id, String name) {
        User user = userService.findByUsername(name);

        if (user == null) {
            log.error("User not found with username: {}", name);
            throw new RuntimeException("User not found");
        }

        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }

    public JournalEntry updateById(JournalEntry journalEntry) {
        return journalEntryRepository.save(journalEntry);
    }
}
