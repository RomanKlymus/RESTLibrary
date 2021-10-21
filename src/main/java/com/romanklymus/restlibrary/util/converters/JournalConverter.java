package com.romanklymus.restlibrary.util.converters;

import com.romanklymus.restlibrary.dto.book.BookInfo;
import com.romanklymus.restlibrary.dto.journal.JournalCreate;
import com.romanklymus.restlibrary.dto.journal.JournalProfile;
import com.romanklymus.restlibrary.dto.user.UserProfile;
import com.romanklymus.restlibrary.entity.Journal;
import com.romanklymus.restlibrary.entity.enums.JournalStatus;
import com.romanklymus.restlibrary.repository.BookRepo;
import com.romanklymus.restlibrary.repository.UserRepo;
import com.romanklymus.restlibrary.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JournalConverter {

    private final DtoConverter dtoConverter;
    private final BookRepo bookRepo;
    private final UserRepo userRepo;

    @Autowired
    public JournalConverter(DtoConverter dtoConverter, BookRepo bookRepo, UserRepo userRepo) {
        this.dtoConverter = dtoConverter;
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
    }

    public JournalProfile mapToProfile(Journal journal) {
        return JournalProfile.builder()
                .id(journal.getId())
                .book(dtoConverter.convertToDto(journal.getBook(), BookInfo.class))
                .user(dtoConverter.convertToDto(journal.getUser(), UserProfile.class))
                .bookReturnDate(journal.getBookReturnDate())
                .expectedReturnDate(journal.getExpectedReturnDate())
                .rentDate(journal.getRentDate())
                .status(journal.getStatus())
                .build();
    }

    public Journal mapToEntity(JournalCreate journal) {
        return Journal.builder()
                .book(bookRepo.getById(journal.getBookId()))
                .status(JournalStatus.REQUESTED)
                .build();
    }
}
