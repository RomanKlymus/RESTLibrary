package com.romanklymus.restlibrary.service.impl;

import com.romanklymus.restlibrary.dto.author.AuthorProfile;
import com.romanklymus.restlibrary.entity.Author;
import com.romanklymus.restlibrary.repository.AuthorRepo;
import com.romanklymus.restlibrary.repository.BookRepo;
import com.romanklymus.restlibrary.service.AuthorService;
import com.romanklymus.restlibrary.util.DtoConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;
    private final DtoConverter dtoConverter;

    @Autowired
    public AuthorServiceImpl(AuthorRepo authorRepo, BookRepo bookRepo, DtoConverter dtoConverter) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public Author addNewAuthor(AuthorProfile authorProfile) {
        if (authorRepo.existsById(authorProfile.getId())) {
            return null;
        }
        log.info("new author created:" + authorProfile);
        return authorRepo.save(dtoConverter.convertToEntity(authorProfile, new Author()));
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepo.getById(id);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    @Override
    public Author editAuthor(Long id, AuthorProfile authorProfile) {
        Author author = getAuthorById(id);
        Author newAuthor = dtoConverter.convertToEntity(authorProfile, new Author()).withId(id);
        BeanUtils.copyProperties(newAuthor, author, "id");
        log.info("**/ author edited to:" + newAuthor);
        return authorRepo.save(author);
    }

    @Override
    public List<Long> getAuthorBooks(Author author) {
        List<Long> list = new ArrayList<>();
        bookRepo.getBooksByMainAuthor(author).forEach(x -> list.add(x.getId()));
        return list;
    }
}
