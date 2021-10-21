package com.romanklymus.restlibrary.dto.book;

import com.romanklymus.restlibrary.dto.author.AuthorProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BookFullInfo {
    private Long id;
    private String title;
    private Long copies;
    private Long averageReadingHours;
    private String description;
    private AuthorProfile author;
    private List<AuthorProfile> coAuthors;
}
