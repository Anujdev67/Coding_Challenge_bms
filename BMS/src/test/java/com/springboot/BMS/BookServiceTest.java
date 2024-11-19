package com.springboot.BMS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.BMS.exception.ResourceNotFoundException;
import com.springboot.BMS.model.Book;
import com.springboot.BMS.repository.BookRepository;
import com.springboot.BMS.service.BookService;

@SpringBootTest
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    private Book book;
    private List<Book> list;

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setIsbn("987654321");
        book.setTitle("Harry Potter");
        book.setAuthor("JK Rowling");
        book.setPublicationYear(2024);

        list = Arrays.asList(book);
    }

    @Test
    public void addBookTest() {
        when(bookRepository.save(book)).thenReturn(book);

        Book newBook = bookService.addBook(book);

        assertNotNull(newBook);
        assertEquals(book.getTitle(), newBook.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void getBookByIsbnTest() {
        when(bookRepository.findById("987654321")).thenReturn(Optional.of(book));

        Optional<Book> foundBook = bookService.getBookByIsbn("987654321");

        assertNotNull(foundBook);
        assertEquals(book.getTitle(), foundBook.get().getTitle());
        verify(bookRepository, times(1)).findById("987654321");
    }

    @Test
    public void getBookByIsbnNotFoundTest() {
        when(bookRepository.findById("123456789")).thenReturn(Optional.empty());

        Optional<Book> foundBook = bookService.getBookByIsbn("123456789");

        assertEquals(Optional.empty(), foundBook);
        verify(bookRepository, times(1)).findById("123456789");
    }

    @Test
    public void getAllBooksTest() {
        when(bookRepository.findAll()).thenReturn(list);

        List<Book> books = bookService.getAllBooks();

        assertNotNull(books);
        assertEquals(1, books.size());
        assertEquals(book.getTitle(), books.get(0).getTitle());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void updateBookTest() {
        when(bookRepository.findById("987654321")).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);

        Book updatedBook = bookService.updateBook("987654321", book);

        assertNotNull(updatedBook);
        assertEquals(book.getTitle(), updatedBook.getTitle());
        verify(bookRepository, times(1)).findById("987654321");
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void deleteBookTest() {
        when(bookRepository.findById("987654321")).thenReturn(Optional.of(book));

        bookService.deleteBook("987654321");

        verify(bookRepository, times(1)).deleteById("987654321");
    }

//    @Test
//    public void deleteBookNotFoundTest() {
//        when(bookRepository.findById("123456789")).thenReturn(Optional.empty());
//
//        assertThrows(ResourceNotFoundException.class, () -> bookService.deleteBook("123456789"));
//
//        verify(bookRepository, times(1)).findById("123456789");
//        verify(bookRepository, times(0)).deleteById("123456789");
//    }
}
