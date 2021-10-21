package com.romanklymus.restlibrary.entity;

import com.romanklymus.restlibrary.dto.marker.Convertible;
import com.romanklymus.restlibrary.entity.enums.JournalStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "journal")
public class Journal implements Convertible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column
    private LocalDate rentDate;

    @Column
    private LocalDate expectedReturnDate;

    @Column
    private LocalDate bookReturnDate;

    @Enumerated(EnumType.STRING)
    private JournalStatus status;
}
