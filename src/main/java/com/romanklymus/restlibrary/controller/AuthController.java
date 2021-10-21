package com.romanklymus.restlibrary.controller;

import com.romanklymus.restlibrary.controller.marker.Api;
import com.romanklymus.restlibrary.dto.auth.AuthRequest;
import com.romanklymus.restlibrary.dto.auth.AuthResponse;
import com.romanklymus.restlibrary.dto.registration.RegistrationRequest;
import com.romanklymus.restlibrary.dto.registration.SuccessRegistrationResponce;
import com.romanklymus.restlibrary.entity.User;
import com.romanklymus.restlibrary.exception.UserNotFoundException;
import com.romanklymus.restlibrary.security.JwtProvider;
import com.romanklymus.restlibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController implements Api {

    private final UserService userService;

    private final JwtProvider jwtProvider;

    @Autowired
    public AuthController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/signup")
    public SuccessRegistrationResponce registerUser(@RequestBody @Valid RegistrationRequest request) {
        return new SuccessRegistrationResponce(userService.saveUser(request).getName());
    }

    @PostMapping("/signin")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        User user = userService.findByEmailAndPassword(request.getEmail(), request.getPassword());
        if (user == null) {
            throw new UserNotFoundException("User not found: " + request);
        }
        String token = jwtProvider.generateToken(user.getEmail(), user.getRole().getName());
        return new AuthResponse(token);
    }
}
