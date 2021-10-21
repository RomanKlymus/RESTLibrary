package com.romanklymus.restlibrary.dto.user;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserInfo {
    private String name;
    private String email;
    private LocalDate birthday;
    private LocalDate registrationDate;
}
