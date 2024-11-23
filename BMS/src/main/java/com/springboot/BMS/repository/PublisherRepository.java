package com.springboot.BMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.BMS.model.Publisher;

public interface PublisherRepository extends  JpaRepository<Publisher, Integer>{

	List<Publisher> findAll();

}
