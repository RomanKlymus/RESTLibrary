package com.romanklymus.restlibrary.service;

import com.romanklymus.restlibrary.dto.registration.RegistrationRequest;
import com.romanklymus.restlibrary.dto.user.UserInfo;
import com.romanklymus.restlibrary.entity.User;

public interface UserService {

    User saveUser(RegistrationRequest request);
    User findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
    UserInfo getUserInfo();

}
