package com.romanklymus.restlibrary.service.impl;

import com.romanklymus.restlibrary.dto.registration.RegistrationRequest;
import com.romanklymus.restlibrary.dto.user.UserInfo;
import com.romanklymus.restlibrary.entity.User;
import com.romanklymus.restlibrary.repository.RoleRepo;
import com.romanklymus.restlibrary.repository.UserRepo;
import com.romanklymus.restlibrary.service.UserService;
import com.romanklymus.restlibrary.util.DtoConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final DtoConverter dtoConverter;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder, DtoConverter dtoConverter) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public User saveUser(RegistrationRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setBirthday(request.getBirthday());
        user.setRole(roleRepo.findByName("ROLE_USER"));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRegistrationDate(LocalDate.now());
        return userRepo.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User user = findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public UserInfo getUserInfo() {
        User user = userRepo.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        return dtoConverter.convertToDto(user, UserInfo.class);
    }
}
