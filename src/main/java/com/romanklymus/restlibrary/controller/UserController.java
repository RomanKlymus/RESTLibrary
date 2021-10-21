package com.romanklymus.restlibrary.controller;

import com.romanklymus.restlibrary.controller.marker.Api;
import com.romanklymus.restlibrary.dto.book.BookInfo;
import com.romanklymus.restlibrary.dto.book.BookWithJournalStatus;
import com.romanklymus.restlibrary.dto.user.UserInfo;
import com.romanklymus.restlibrary.dto.user.UserProfile;
import com.romanklymus.restlibrary.repository.UserRepo;
import com.romanklymus.restlibrary.service.JournalService;
import com.romanklymus.restlibrary.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class UserController implements Api {

    private final JournalService journalService;
    private final UserRepo userRepo;
    private final UserService userService;

    @Autowired
    public UserController(JournalService journalService, UserRepo userRepo, UserService userService) {
        this.journalService = journalService;
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @GetMapping("/user/books")
    public List<BookWithJournalStatus> getUserBooks() {
        return journalService.getUserBooks(
                userRepo.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
    }

    @GetMapping("/user")
    public UserInfo getUserInfo() {
        return userService.getUserInfo();
    }
}
