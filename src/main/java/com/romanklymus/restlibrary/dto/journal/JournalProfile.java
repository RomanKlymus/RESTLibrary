package com.romanklymus.restlibrary.dto.journal;

import com.romanklymus.restlibrary.dto.book.BookInfo;
import com.romanklymus.restlibrary.dto.marker.Convertible;
import com.romanklymus.restlibrary.dto.user.UserProfile;
import com.romanklymus.restlibrary.entity.enums.JournalStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class JournalProfile implements Convertible {

    private Long id;
    private UserProfile user;
    private BookInfo book;
    private LocalDate rentDate;
    private LocalDate expectedReturnDate;
    private LocalDate bookReturnDate;
    private JournalStatus status;

}
