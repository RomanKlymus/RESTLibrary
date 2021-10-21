package com.romanklymus.restlibrary.controller;

import com.romanklymus.restlibrary.controller.marker.Api;
import com.romanklymus.restlibrary.dto.book.BookFullInfo;
import com.romanklymus.restlibrary.dto.book.BookProfile;
import com.romanklymus.restlibrary.entity.Book;
import com.romanklymus.restlibrary.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class BookController implements Api {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book/{id}")
    public BookFullInfo getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/book")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public BookProfile addBook(@RequestBody BookProfile bookProfile) {
        return bookService.addNewBook(bookProfile);
    }

    @DeleteMapping("/book/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteBook(@PathVariable("id") Book book) {
        bookService.deleteBook(book);
    }

}
