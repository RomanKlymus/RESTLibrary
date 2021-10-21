package com.romanklymus.restlibrary.repository;

import com.romanklymus.restlibrary.entity.Author;
import com.romanklymus.restlibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Long> {

}
