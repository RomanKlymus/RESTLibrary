package com.romanklymus.restlibrary.service;

import com.romanklymus.restlibrary.dto.book.BookFullInfo;
import com.romanklymus.restlibrary.dto.book.BookProfile;
import com.romanklymus.restlibrary.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    BookProfile addNewBook(BookProfile bookProfile);

    BookFullInfo getBookById(Long id);

    void deleteBook(Book book);

    void changeCopies(Long id, Long newCopies);
}
