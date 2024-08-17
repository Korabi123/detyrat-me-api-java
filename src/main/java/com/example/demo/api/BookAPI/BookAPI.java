package com.example.demo.BookAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookAPI {

    private final List<BookModel> books = new ArrayList<>();

    @PostMapping("/api/books/create")
    public List<BookModel> createBook(@RequestBody List<BookModel> newBooks) {
        for (BookModel bookModel : newBooks) {
            this.books.add(bookModel);
        }
        return this.books;
    }

    @GetMapping("/api/books/get")
    public List<BookModel> getBooks(
            @RequestParam(value = "bookId", required = false) String bookId,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "publisher", required = false) String publisher
    ) {
        return books.stream()
                .filter(book -> (bookId == null || book.getId().equals(bookId))
                && (author == null || book.getAuthor().equals(author))
                && (title == null || book.getTitle().equals(title))
                && (publisher == null || book.getPublisher().equals(publisher)))
                .collect(Collectors.toList());
    }

    @PatchMapping("/api/books/update")
    public List<BookModel> updateBook(@RequestParam("bookId") String bookId, @RequestBody BookModel updatedBook) {
        books.stream()
                .filter(book -> book.getId().equals(bookId))
                .findFirst()
                .ifPresent(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                    book.setPublisher(updatedBook.getPublisher());
                });
        return books;
    }

    @DeleteMapping("/api/books/delete")
    public List<BookModel> deleteBook(@RequestParam("bookId") String bookId) {
        books.removeIf(book -> book.getId().equals(bookId));
        return books;
    }
}
