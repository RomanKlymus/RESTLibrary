package com.romanklymus.restlibrary.dto.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.romanklymus.restlibrary.dto.marker.Convertible;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.List;

@Data
@With
@AllArgsConstructor
@NoArgsConstructor
public class BookProfile implements Convertible {

    private Long id;

    private String title;

    private Long copies;

    private String description;

    private Long averageReadingHours;

    private Long authorId;

    private List<Long> coAuthorsId;

}
