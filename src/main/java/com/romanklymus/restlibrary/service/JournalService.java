package com.romanklymus.restlibrary.service;

import com.romanklymus.restlibrary.dto.book.BookInfo;
import com.romanklymus.restlibrary.dto.book.BookWithJournalStatus;
import com.romanklymus.restlibrary.dto.journal.JournalCreate;
import com.romanklymus.restlibrary.dto.journal.JournalProfile;
import com.romanklymus.restlibrary.dto.journal.Status;
import com.romanklymus.restlibrary.entity.Journal;
import com.romanklymus.restlibrary.entity.User;

import java.util.List;

public interface JournalService {

    List<JournalProfile> getAllRecords();

    JournalCreate addRecord(JournalCreate journalCreate, User user);

    void changeStatus(Journal journal, Status status);

    List<BookWithJournalStatus> getUserBooks(User user);
}
