package com.springboot.BMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.BMS.model.Book;
import com.springboot.BMS.model.Publisher;
import com.springboot.BMS.service.BookService;
import com.springboot.BMS.service.PublisherService;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;
@Autowired
private PublisherService publisherService;
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<Book> updateBook(@PathVariable String isbn, @RequestBody Book bookDetails) {
        return ResponseEntity.ok(bookService.updateBook(isbn, bookDetails));
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/home")
    public String home() {
    	return "this is home page";
    }
    
  //userwise books
    @GetMapping("/user/{userId}")
    public List<Book> getBooksByUser(@PathVariable int userId){
    	return bookService.getBooksByUser(userId);
    }
    @GetMapping("/publishers")
    public List<Publisher> getAllPublishers() {
        return publisherService.getAllPublisher();
    }
    
}
