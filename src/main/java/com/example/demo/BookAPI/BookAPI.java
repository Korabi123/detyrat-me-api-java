package com.example.demo.BookAPI;

import java.util.ArrayList;
import java.util.List;

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
    public List<BookModel> createBook(@RequestBody List<BookModel> book) {
       for (BookModel bookModel : book) {
           this.books.add(bookModel);
           return book;
       }
       return books;
    }

    @GetMapping("/api/books/get")
    public List<BookModel> getBooks() {
        return books;
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
