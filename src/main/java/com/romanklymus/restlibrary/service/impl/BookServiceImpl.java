package com.romanklymus.restlibrary.service.impl;

import com.romanklymus.restlibrary.dto.book.BookFullInfo;
import com.romanklymus.restlibrary.dto.book.BookProfile;
import com.romanklymus.restlibrary.entity.Book;
import com.romanklymus.restlibrary.repository.BookRepo;
import com.romanklymus.restlibrary.service.BookService;
import com.romanklymus.restlibrary.util.converters.BookConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    private final BookConverter bookConverter;

    @Autowired
    public BookServiceImpl(BookRepo bookRepo, BookConverter bookConverter) {
        this.bookRepo = bookRepo;
        this.bookConverter = bookConverter;
    }


    @Override
    public List<Book> getAllBooks() {
        return bookRepo.getBooksByIsAvailableTrue();
    }

    @Override
    public BookProfile addNewBook(BookProfile bookProfile) {
        Book newBook = bookConverter.mapBookProfileToBook(bookProfile);
        log.info("**/ book created " + newBook);
        bookRepo.save(newBook);
        return bookProfile.withId(newBook.getId());
    }

    @Override
    public BookFullInfo getBookById(Long id) {
        return bookConverter.mapBookToBookFullInfo(bookRepo.getById(id));
    }

    @Override
    public void deleteBook(Book book) {
        book.setIsAvailable(false);
        bookRepo.save(book);
    }

    @Override
    public void changeCopies(Long id, Long newCopies) {
        Book book = bookRepo.getById(id);
        book.setCopies(newCopies);
        book.setIsAvailable(newCopies >= 1);
        bookRepo.save(book);
    }


}
