package com.romanklymus.restlibrary.dto.author;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.romanklymus.restlibrary.dto.marker.Convertible;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorProfile implements Convertible {
    private Long id;
    private String name;
}
