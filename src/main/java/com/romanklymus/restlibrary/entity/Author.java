package com.romanklymus.restlibrary.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.romanklymus.restlibrary.dto.marker.Convertible;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@With
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "author")
public class Author implements Convertible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "mainAuthor", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Book> books;
}
