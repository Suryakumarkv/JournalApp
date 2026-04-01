package com.surya.journalApp.Service;

import com.surya.journalApp.Entity.JournalEntry;
import com.surya.journalApp.Repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class JournalEntryService {

    @Autowired
   private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry) {
        try {
            journalEntry.setDate(LocalDateTime.now());
            journalEntryRepository.save(journalEntry);
            System.out.println("Saved to MongoDB");
        }catch (Exception e){
            log.error("Exception", e);
        }

    }

    public List<JournalEntry> findAll() {
        return journalEntryRepository.findAll();
    }

    public JournalEntry findById(ObjectId id) {
        return journalEntryRepository.findById(id).get();
    }

    public void deleteById(ObjectId id) {
         journalEntryRepository.deleteById(id);
    }

    public JournalEntry updateById(JournalEntry journalEntry) {
        return journalEntryRepository.save(journalEntry);
    }
}
