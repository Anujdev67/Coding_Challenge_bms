package com.springboot.BMS.repository;

import com.springboot.BMS.model.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {

	List<Book> findByUserId(int userId);
	
}

