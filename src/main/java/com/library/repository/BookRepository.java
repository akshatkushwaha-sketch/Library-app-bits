package com.library.repository;

import com.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByGenreIgnoreCase(String genre);

    // Custom JPQL inner join - fetches books along with their author details
    @Query("SELECT b FROM Book b INNER JOIN b.author a WHERE a.nationality = :nationality")
    List<Book> findBooksByAuthorNationality(@Param("nationality") String nationality);

    List<Book> findByAuthorId(Long authorId);
}
