package com.library.service;

import com.library.entity.Author;
import com.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long id, Author updated) {
        Author existing = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found: " + id));
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setNationality(updated.getNationality());
        return authorRepository.save(existing);
    }

    public List<Author> findByNationality(String nationality) {
        return authorRepository.findByNationalityIgnoreCase(nationality);
    }

    public List<Author> findAuthorsWithBooksOfGenre(String genre) {
        return authorRepository.findAuthorsWithBooksOfGenre(genre);
    }

    public boolean emailAlreadyExists(String email) {
        return authorRepository.findByEmail(email).isPresent();
    }
}
