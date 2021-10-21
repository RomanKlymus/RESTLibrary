package com.romanklymus.restlibrary.repository;

import com.romanklymus.restlibrary.entity.Book;
import com.romanklymus.restlibrary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    User findUserByEmail(String email);

}
