package com.romanklymus.restlibrary.repository;

import com.romanklymus.restlibrary.entity.Book;
import com.romanklymus.restlibrary.entity.Journal;
import com.romanklymus.restlibrary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JournalRepo extends JpaRepository<Journal, Long> {

    List<Journal> getByUser(User user);
}
