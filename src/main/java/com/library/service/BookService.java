package com.library.service;

import com.library.entity.Author;
import com.library.entity.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updated) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found: " + id));
        existing.setTitle(updated.getTitle());
        existing.setGenre(updated.getGenre());
        existing.setPublishedYear(updated.getPublishedYear());
        if (updated.getAuthor() != null && updated.getAuthor().getId() != null) {
            Author author = authorService.getAuthorById(updated.getAuthor().getId())
                    .orElseThrow(() -> new RuntimeException("Author not found"));
            existing.setAuthor(author);
        }
        return bookRepository.save(existing);
    }

    public List<Book> findBooksByAuthorNationality(String nationality) {
        return bookRepository.findBooksByAuthorNationality(nationality);
    }

    public List<Book> findByGenre(String genre) {
        return bookRepository.findByGenreIgnoreCase(genre);
    }
}
