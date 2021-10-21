package com.romanklymus.restlibrary.service;

import com.romanklymus.restlibrary.dto.author.AuthorProfile;
import com.romanklymus.restlibrary.entity.Author;
import com.romanklymus.restlibrary.entity.Book;

import java.util.List;

public interface AuthorService {

    Author addNewAuthor(AuthorProfile authorProfile);

    Author getAuthorById(Long id);

    List<Author> getAllAuthors();

    Author editAuthor(Long id, AuthorProfile authorProfile);

    List<Long> getAuthorBooks(Author author);
}
