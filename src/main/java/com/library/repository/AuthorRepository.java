package com.library.repository;

import com.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByEmail(String email);

    List<Author> findByNationalityIgnoreCase(String nationality);

    // Custom JPQL query - inner join between authors and books
    @Query("SELECT DISTINCT a FROM Author a INNER JOIN a.books b WHERE b.genre = :genre")
    List<Author> findAuthorsWithBooksOfGenre(@Param("genre") String genre);
}
