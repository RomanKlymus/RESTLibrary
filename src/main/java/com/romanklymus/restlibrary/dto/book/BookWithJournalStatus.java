package com.romanklymus.restlibrary.dto.book;

import com.romanklymus.restlibrary.entity.enums.JournalStatus;
import lombok.Data;

@Data
public class BookWithJournalStatus {
    private Long id;
    private String title;
    private JournalStatus status;
}
