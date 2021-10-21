package com.romanklymus.restlibrary.entity;

import com.romanklymus.restlibrary.dto.marker.Convertible;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@With
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User implements Convertible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false)
    @CreatedDate
    private LocalDate registrationDate;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
