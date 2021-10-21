package com.romanklymus.restlibrary.dto.registration;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
public class RegistrationRequest {
    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    private LocalDate birthday;
}
