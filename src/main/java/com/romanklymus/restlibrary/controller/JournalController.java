package com.romanklymus.restlibrary.controller;

import com.romanklymus.restlibrary.controller.marker.Api;
import com.romanklymus.restlibrary.dto.journal.JournalCreate;
import com.romanklymus.restlibrary.dto.journal.JournalProfile;
import com.romanklymus.restlibrary.dto.journal.Status;
import com.romanklymus.restlibrary.entity.Journal;
import com.romanklymus.restlibrary.repository.UserRepo;
import com.romanklymus.restlibrary.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JournalController implements Api {

    private final JournalService journalService;
    private final UserRepo userRepo;

    @Autowired
    public JournalController(JournalService journalService, UserRepo userRepo) {
        this.journalService = journalService;
        this.userRepo = userRepo;
    }

    @GetMapping("/journal")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<JournalProfile> getAllRecords() {
        return journalService.getAllRecords();
    }

    @PostMapping("/journal")
    public JournalCreate addRecord(@RequestBody JournalCreate journalCreate) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return journalService.addRecord(journalCreate,
                userRepo.findUserByEmail(username));
    }

    @PatchMapping("/journal/{id}")
    public void changeStatus(@PathVariable("id") Journal journal, @RequestBody Status status) {
        journalService.changeStatus(journal, status);
    }

}
