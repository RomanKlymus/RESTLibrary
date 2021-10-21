package com.romanklymus.restlibrary.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.romanklymus.restlibrary.dto.marker.Convertible;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@With
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book implements Convertible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long copies;

    @Column(nullable = false)
    private Long averageReadingHours;

    @Column(nullable = false)
    private Boolean isAvailable;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "main_author_id")
    @ToString.Exclude
    @JsonManagedReference
    private Author mainAuthor;

    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Author> coAuthors;

}
