package com.romanklymus.restlibrary.repository;

import com.romanklymus.restlibrary.entity.Author;
import com.romanklymus.restlibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long> {

    List<Book> getByTitleLike(String title);

    List<Book> getBooksByMainAuthor(Author author);

    @Query("select b from Book b join Author a on b.mainAuthor.id=a.id where a.name like ?1")
    List<Book> getBooksByAuthorName(String name);

    List<Book> getBooksByIsAvailableTrue();

}
