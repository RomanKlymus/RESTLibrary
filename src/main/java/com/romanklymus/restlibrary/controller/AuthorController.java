package com.romanklymus.restlibrary.controller;

import com.romanklymus.restlibrary.controller.marker.Api;
import com.romanklymus.restlibrary.dto.author.AuthorProfile;
import com.romanklymus.restlibrary.entity.Author;
import com.romanklymus.restlibrary.entity.Book;
import com.romanklymus.restlibrary.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class AuthorController implements Api {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author/{id}")
    public Author getAuthorById(@PathVariable("id") Author author) {
        return author;
    }

    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @PostMapping("/author")
    public Author addAuthor(@RequestBody AuthorProfile author) {
        return authorService.addNewAuthor(author);
    }

    @PutMapping("/author/{id}")
    public Author editAuthor(@PathVariable Long id, @RequestBody AuthorProfile authorProfile) {
        return authorService.editAuthor(id, authorProfile);
    }

    @GetMapping("/author/{id}/books")
    public List<Long> getAuthorBooks(@PathVariable("id") Author author) {
        return authorService.getAuthorBooks(author);
    }
}
