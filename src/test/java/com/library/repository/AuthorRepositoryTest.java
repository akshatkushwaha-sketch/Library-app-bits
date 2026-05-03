package com.library.repository;

import com.library.entity.Author;
import com.library.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    void shouldSaveAndFindAuthorById() {
        Author author = authorRepository.save(new Author("Test Author", "test@mail.com", "British"));
        Optional<Author> found = authorRepository.findById(author.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Test Author");
    }

    @Test
    void shouldFindAuthorByEmail() {
        authorRepository.save(new Author("Jane Austen", "jausten@mail.com", "British"));
        Optional<Author> result = authorRepository.findByEmail("jausten@mail.com");
        assertThat(result).isPresent();
        assertThat(result.get().getNationality()).isEqualTo("British");
    }

    @Test
    void shouldReturnEmptyWhenEmailNotFound() {
        Optional<Author> result = authorRepository.findByEmail("nobody@mail.com");
        assertThat(result).isEmpty();
    }

    @Test
    void shouldFindAuthorsByNationality() {
        authorRepository.save(new Author("Author A", "a@mail.com", "American"));
        authorRepository.save(new Author("Author B", "b@mail.com", "American"));
        authorRepository.save(new Author("Author C", "c@mail.com", "British"));

        List<Author> americans = authorRepository.findByNationalityIgnoreCase("american");
        assertThat(americans).hasSize(2);
    }

    @Test
    void shouldFindAuthorsWithBooksOfGenre() {
        Author author = authorRepository.save(new Author("Test Writer", "tw@mail.com", "Japanese"));
        bookRepository.save(new Book("Book One", "Fantasy", 2000, author));
        bookRepository.save(new Book("Book Two", "Romance", 2005, author));

        List<Author> result = authorRepository.findAuthorsWithBooksOfGenre("Fantasy");
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Test Writer");
    }

    @Test
    void shouldReturnAllAuthors() {
        authorRepository.save(new Author("Writer 1", "w1@mail.com", "French"));
        authorRepository.save(new Author("Writer 2", "w2@mail.com", "German"));
        assertThat(authorRepository.findAll()).hasSize(2);
    }
}
