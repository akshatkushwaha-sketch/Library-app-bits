package com.library.controller;

import com.library.entity.Author;
import com.library.entity.Book;
import com.library.service.AuthorService;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "book/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "book/add";
    }

    @PostMapping("/add")
    public String addBook(@RequestParam String title,
                          @RequestParam String genre,
                          @RequestParam int publishedYear,
                          @RequestParam Long authorId,
                          RedirectAttributes redirectAttrs) {
        try {
            Author author = authorService.getAuthorById(authorId)
                    .orElseThrow(() -> new RuntimeException("Author not found"));
            bookService.saveBook(new Book(title, genre, publishedYear, author));
            redirectAttrs.addFlashAttribute("success", "Book added successfully!");
        } catch (DataIntegrityViolationException e) {
            redirectAttrs.addFlashAttribute("error", "Could not save the book – integrity error.");
            return "redirect:/books/add";
        }
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAllAuthors());
        return "book/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id,
                             @RequestParam String title,
                             @RequestParam String genre,
                             @RequestParam int publishedYear,
                             @RequestParam Long authorId,
                             RedirectAttributes redirectAttrs) {
        try {
            Author author = authorService.getAuthorById(authorId)
                    .orElseThrow(() -> new RuntimeException("Author not found"));
            Book updated = new Book(title, genre, publishedYear, author);
            bookService.updateBook(id, updated);
            redirectAttrs.addFlashAttribute("success", "Book updated successfully!");
        } catch (DataIntegrityViolationException e) {
            redirectAttrs.addFlashAttribute("error", "Update failed – integrity violation.");
            return "redirect:/books/edit/" + id;
        }
        return "redirect:/books";
    }
}
