package com.library.controller;

import com.library.entity.Author;
import com.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return "author/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("author", new Author());
        return "author/add";
    }

    @PostMapping("/add")
    public String addAuthor(@ModelAttribute Author author,
                            RedirectAttributes redirectAttrs) {
        try {
            if (authorService.emailAlreadyExists(author.getEmail())) {
                redirectAttrs.addFlashAttribute("error",
                        "An author with email '" + author.getEmail() + "' already exists.");
                return "redirect:/authors/add";
            }
            authorService.saveAuthor(author);
            redirectAttrs.addFlashAttribute("success", "Author added successfully!");
        } catch (DataIntegrityViolationException e) {
            redirectAttrs.addFlashAttribute("error", "Duplicate entry – please check the email address.");
            return "redirect:/authors/add";
        }
        return "redirect:/authors";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Author author = authorService.getAuthorById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        model.addAttribute("author", author);
        return "author/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateAuthor(@PathVariable Long id,
                               @ModelAttribute Author author,
                               RedirectAttributes redirectAttrs) {
        try {
            authorService.updateAuthor(id, author);
            redirectAttrs.addFlashAttribute("success", "Author updated successfully!");
        } catch (DataIntegrityViolationException e) {
            redirectAttrs.addFlashAttribute("error", "Update failed – duplicate email address.");
            return "redirect:/authors/edit/" + id;
        }
        return "redirect:/authors";
    }
}
