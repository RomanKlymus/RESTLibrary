package com.romanklymus.restlibrary.util.converters;

import com.romanklymus.restlibrary.dto.author.AuthorProfile;
import com.romanklymus.restlibrary.dto.book.BookFullInfo;
import com.romanklymus.restlibrary.dto.book.BookProfile;
import com.romanklymus.restlibrary.entity.Book;
import com.romanklymus.restlibrary.repository.AuthorRepo;
import com.romanklymus.restlibrary.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookConverter {

    private final AuthorRepo authorRepo;
    private final DtoConverter dtoConverter;

    @Autowired
    public BookConverter(AuthorRepo authorRepo, DtoConverter dtoConverter) {
        this.authorRepo = authorRepo;
        this.dtoConverter = dtoConverter;
    }

    public BookProfile mapBookToBookProfile(Book book) {
        BookProfile bookProfile = new BookProfile();
        bookProfile.setId(book.getId());
        bookProfile.setTitle(book.getTitle());
        bookProfile.setCopies(book.getCopies());
        bookProfile.setAuthorId(book.getMainAuthor().getId());
        bookProfile.setDescription(book.getDescription());
        bookProfile.setAverageReadingHours(book.getAverageReadingHours());
        bookProfile.setCoAuthorsId(new ArrayList<>());
        book.getCoAuthors().forEach(x -> bookProfile.getCoAuthorsId().add(x.getId()));
        return bookProfile;
    }

    public BookFullInfo mapBookToBookFullInfo(Book book) {
        BookFullInfo bookFullInfo = new BookFullInfo();
        bookFullInfo.setId(book.getId());
        bookFullInfo.setTitle(book.getTitle());
        bookFullInfo.setCopies(book.getCopies());
        bookFullInfo.setAuthor(dtoConverter.convertToDto(book.getMainAuthor(), AuthorProfile.class));
        bookFullInfo.setDescription(book.getDescription());
        bookFullInfo.setAverageReadingHours(book.getAverageReadingHours());
        List<AuthorProfile> list = new ArrayList<>();
        book.getCoAuthors().forEach((a) -> list.add(dtoConverter.convertToDto(a, AuthorProfile.class)));
        bookFullInfo.setCoAuthors(list);
        return bookFullInfo;
    }

    public Book mapBookProfileToBook(BookProfile bookProfile) {
        Book book = new Book();
        book.setId(bookProfile.getId());
        book.setTitle(bookProfile.getTitle());
        book.setCopies(bookProfile.getCopies());
        book.setDescription(bookProfile.getDescription());
        book.setAverageReadingHours(bookProfile.getAverageReadingHours());
        book.setMainAuthor(authorRepo.getById(bookProfile.getAuthorId()));
        book.setIsAvailable(true);
        book.setCoAuthors(new ArrayList<>());
        bookProfile.getCoAuthorsId().forEach(x -> book.getCoAuthors().add(authorRepo.getById(x)));
        return book;
    }
}
