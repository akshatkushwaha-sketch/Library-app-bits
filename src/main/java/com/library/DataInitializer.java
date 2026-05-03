package com.library;

import com.library.entity.Author;
import com.library.entity.Book;
import com.library.repository.AuthorRepository;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) {
        if (authorRepository.count() > 0) return;

        Author a1  = authorRepository.save(new Author("George Orwell",      "gorwell@mail.com",    "British"));
        Author a2  = authorRepository.save(new Author("J.K. Rowling",       "jkrowling@mail.com",  "British"));
        Author a3  = authorRepository.save(new Author("Ernest Hemingway",   "ehemingway@mail.com", "American"));
        Author a4  = authorRepository.save(new Author("Haruki Murakami",    "hmurakami@mail.com",  "Japanese"));
        Author a5  = authorRepository.save(new Author("Gabriel Garcia Marquez", "gmarquez@mail.com", "Colombian"));
        Author a6  = authorRepository.save(new Author("Toni Morrison",      "tmorrison@mail.com",  "American"));
        Author a7  = authorRepository.save(new Author("Franz Kafka",        "fkafka@mail.com",     "Czech"));
        Author a8  = authorRepository.save(new Author("Virginia Woolf",     "vwoolf@mail.com",     "British"));
        Author a9  = authorRepository.save(new Author("Leo Tolstoy",        "ltolstoy@mail.com",   "Russian"));
        Author a10 = authorRepository.save(new Author("Fyodor Dostoevsky",  "fdost@mail.com",      "Russian"));

        bookRepository.save(new Book("1984",                          "Dystopian",        1949, a1));
        bookRepository.save(new Book("Animal Farm",                   "Political Satire", 1945, a1));
        bookRepository.save(new Book("Harry Potter and the Sorcerer's Stone", "Fantasy", 1997, a2));
        bookRepository.save(new Book("The Old Man and the Sea",       "Fiction",          1952, a3));
        bookRepository.save(new Book("Norwegian Wood",                "Romance",          1987, a4));
        bookRepository.save(new Book("Kafka on the Shore",            "Magical Realism",  2002, a4));
        bookRepository.save(new Book("One Hundred Years of Solitude", "Magical Realism",  1967, a5));
        bookRepository.save(new Book("Beloved",                       "Historical",       1987, a6));
        bookRepository.save(new Book("The Trial",                     "Fiction",          1925, a7));
        bookRepository.save(new Book("Mrs Dalloway",                  "Modernist",        1925, a8));
    }
}
