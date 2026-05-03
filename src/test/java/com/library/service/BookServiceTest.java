package com.library.service;

import com.library.entity.Author;
import com.library.entity.Book;
import com.library.repository.BookRepository;
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
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private BookService bookService;

    @Test
    void shouldReturnAllBooks() {
        Author author = new Author("Some Author", "s@mail.com", "British");
        List<Book> mockBooks = List.of(
                new Book("Book A", "Fiction", 2001, author),
                new Book("Book B", "Fantasy", 2005, author)
        );
        when(bookRepository.findAll()).thenReturn(mockBooks);

        List<Book> result = bookService.getAllBooks();

        assertThat(result).hasSize(2);
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnBookById() {
        Author author = new Author("Author", "a@mail.com", "French");
        Book book = new Book("Some Book", "Drama", 1999, author);
        book.setId(3L);
        when(bookRepository.findById(3L)).thenReturn(Optional.of(book));

        Optional<Book> result = bookService.getBookById(3L);

        assertThat(result).isPresent();
        assertThat(result.get().getTitle()).isEqualTo("Some Book");
    }

    @Test
    void shouldSaveBook() {
        Author author = new Author("Author", "a@mail.com", "German");
        Book book = new Book("New Book", "Thriller", 2020, author);
        when(bookRepository.save(book)).thenReturn(book);

        Book saved = bookService.saveBook(book);

        assertThat(saved.getTitle()).isEqualTo("New Book");
        verify(bookRepository).save(book);
    }

    @Test
    void shouldUpdateBookDetails() {
        Author author = new Author("Author", "a@mail.com", "Italian");
        author.setId(2L);

        Book existing = new Book("Old Title", "Drama", 2000, author);
        existing.setId(10L);

        Book updates = new Book("New Title", "Thriller", 2022, author);
        updates.setAuthor(author);

        when(bookRepository.findById(10L)).thenReturn(Optional.of(existing));
        when(authorService.getAuthorById(2L)).thenReturn(Optional.of(author));
        when(bookRepository.save(any(Book.class))).thenAnswer(inv -> inv.getArgument(0));

        Book result = bookService.updateBook(10L, updates);

        assertThat(result.getTitle()).isEqualTo("New Title");
        assertThat(result.getGenre()).isEqualTo("Thriller");
        assertThat(result.getPublishedYear()).isEqualTo(2022);
    }

    @Test
    void shouldThrowWhenUpdatingNonExistentBook() {
        when(bookRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookService.updateBook(999L, new Book()));
    }

    @Test
    void shouldFindBooksByAuthorNationality() {
        Author author = new Author("Russian Author", "r@mail.com", "Russian");
        List<Book> books = List.of(new Book("Russian Book", "Classic", 1880, author));
        when(bookRepository.findBooksByAuthorNationality("Russian")).thenReturn(books);

        List<Book> result = bookService.findBooksByAuthorNationality("Russian");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Russian Book");
    }
}
