package com.surya.journalApp.Controller;


import com.surya.journalApp.Entity.JournalEntry;
import com.surya.journalApp.Repository.JournalEntryRepository;
import com.surya.journalApp.Service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService  journalEntryService;

    @GetMapping("/findAll")
    public ResponseEntity<?> getAll() {
        List<JournalEntry> all = journalEntryService.findAll();
        if(all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/add")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
        journalEntryService.saveEntry(myEntry);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId id) {
          Optional<JournalEntry> journalEntry= Optional.ofNullable(journalEntryService.findById(id));
          if(journalEntry.isPresent()) {
              return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
          }
          else {
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId id) {
        journalEntryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<JournalEntry> updateById(@PathVariable ObjectId id,@RequestBody JournalEntry newEntry) {

        JournalEntry oldEntry = journalEntryService.findById(id);

        if(oldEntry!=null) {
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
            journalEntryService.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        }
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
