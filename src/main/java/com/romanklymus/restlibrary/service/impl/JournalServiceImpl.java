package com.romanklymus.restlibrary.service.impl;

import com.romanklymus.restlibrary.dto.book.BookWithJournalStatus;
import com.romanklymus.restlibrary.dto.journal.JournalCreate;
import com.romanklymus.restlibrary.dto.journal.JournalProfile;
import com.romanklymus.restlibrary.dto.journal.Status;
import com.romanklymus.restlibrary.entity.Journal;
import com.romanklymus.restlibrary.entity.User;
import com.romanklymus.restlibrary.entity.enums.JournalStatus;
import com.romanklymus.restlibrary.repository.JournalRepo;
import com.romanklymus.restlibrary.service.BookService;
import com.romanklymus.restlibrary.service.JournalService;
import com.romanklymus.restlibrary.util.converters.JournalConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class JournalServiceImpl implements JournalService {

    private final JournalRepo journalRepo;
    private final JournalConverter journalConverter;
    private final BookService bookService;

    @Autowired
    public JournalServiceImpl(JournalRepo journalRepo, JournalConverter journalConverter, BookService bookService) {
        this.journalRepo = journalRepo;
        this.journalConverter = journalConverter;
        this.bookService = bookService;
    }

    @Override
    public List<JournalProfile> getAllRecords() {
        List<JournalProfile> list = new ArrayList<>();
        journalRepo.findAll().forEach((x) -> list.add(journalConverter.mapToProfile(x)));
        return list;
    }

    @Override
    public JournalCreate addRecord(JournalCreate journalCreate, User user) {
        Journal journal = journalConverter.mapToEntity(journalCreate);
        journal.setUser(user);
        journalRepo.save(journal);
        return journalCreate;
    }

    @Override
    public void changeStatus(Journal journal, Status status) {
        JournalStatus journalStatus = JournalStatus.valueOf(status.getStatus());
        if (journalStatus.equals(JournalStatus.GIVEN)) {
            journal.setRentDate(LocalDate.now());
            journal.setExpectedReturnDate(LocalDate.now().plusDays(30));
            bookService.changeCopies(journal.getBook().getId(), journal.getBook().getCopies() - 1);
        }
        if (journalStatus.equals(JournalStatus.RETURNED)) {
            journal.setBookReturnDate(LocalDate.now());
            bookService.changeCopies(journal.getBook().getId(), journal.getBook().getCopies() + 1);
        }
        journal.setStatus(journalStatus);
        journalRepo.save(journal);
    }

    @Override
    public List<BookWithJournalStatus> getUserBooks(User user) {
        List<BookWithJournalStatus> list = new ArrayList<>();
        journalRepo.getByUser(user).forEach((x) -> {
            BookWithJournalStatus book = new BookWithJournalStatus();
            book.setId(x.getId());
            book.setTitle(x.getBook().getTitle());
            book.setStatus(x.getStatus());
            list.add(book);
        });
        return list;
    }
}