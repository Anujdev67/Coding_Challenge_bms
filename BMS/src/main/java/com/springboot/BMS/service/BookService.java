package com.springboot.BMS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.BMS.model.Book;
import com.springboot.BMS.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	public Optional<Book> getBookByIsbn(String isbn) {
		return bookRepository.findById(isbn);
	}

	public Book addBook(Book book) {
		return bookRepository.save(book);
	}

	public Book updateBook(String isbn, Book bookDetails) {
		Book book = bookRepository.findById(isbn).orElseThrow();
		book.setTitle(bookDetails.getTitle());
		book.setAuthor(bookDetails.getAuthor());
		book.setPublicationYear(bookDetails.getPublicationYear());

		return bookRepository.save(book);
	}

	public void deleteBook(String isbn) {
		bookRepository.deleteById(isbn);
	}

	public List<Book> getBooksByUser(int userId) {
		// TODO Auto-generated method stub
		return bookRepository.findByUserId(userId);
	}

}
