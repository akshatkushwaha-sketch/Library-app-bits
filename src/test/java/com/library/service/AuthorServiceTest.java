package com.library.service;

import com.library.entity.Author;
import com.library.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void shouldReturnAllAuthors() {
        List<Author> mockList = List.of(
                new Author("Author 1", "a1@mail.com", "British"),
                new Author("Author 2", "a2@mail.com", "American")
        );
        when(authorRepository.findAll()).thenReturn(mockList);

        List<Author> result = authorService.getAllAuthors();

        assertThat(result).hasSize(2);
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnAuthorById() {
        Author author = new Author("Test", "test@mail.com", "French");
        author.setId(1L);
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        Optional<Author> result = authorService.getAuthorById(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Test");
    }

    @Test
    void shouldSaveAuthor() {
        Author author = new Author("New Author", "new@mail.com", "Italian");
        when(authorRepository.save(author)).thenReturn(author);

        Author saved = authorService.saveAuthor(author);

        assertThat(saved.getName()).isEqualTo("New Author");
        verify(authorRepository).save(author);
    }

    @Test
    void shouldUpdateAuthorFields() {
        Author existing = new Author("Old Name", "old@mail.com", "British");
        existing.setId(5L);

        Author updates = new Author("New Name", "new@mail.com", "American");

        when(authorRepository.findById(5L)).thenReturn(Optional.of(existing));
        when(authorRepository.save(any(Author.class))).thenAnswer(inv -> inv.getArgument(0));

        Author result = authorService.updateAuthor(5L, updates);

        assertThat(result.getName()).isEqualTo("New Name");
        assertThat(result.getEmail()).isEqualTo("new@mail.com");
        assertThat(result.getNationality()).isEqualTo("American");
    }

    @Test
    void shouldThrowWhenUpdatingNonExistentAuthor() {
        when(authorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> authorService.updateAuthor(99L, new Author()));
    }

    @Test
    void shouldDetectExistingEmail() {
        when(authorRepository.findByEmail("taken@mail.com"))
                .thenReturn(Optional.of(new Author()));

        assertThat(authorService.emailAlreadyExists("taken@mail.com")).isTrue();
        assertThat(authorService.emailAlreadyExists("free@mail.com")).isFalse();
    }
}
