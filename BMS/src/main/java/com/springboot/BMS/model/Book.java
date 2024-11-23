package com.springboot.BMS.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {
    @Id
    private String isbn;
    private String title;
    private String author;
    private int publicationYear;
    public String getIsbn() {
		return isbn;
	}
	@ManyToOne
	private User user;
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(String isbn, String title, String author, int publicationYear) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publicationYear = publicationYear;
	}

}
