package com.springboot.BMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.BMS.model.Publisher;
import com.springboot.BMS.repository.PublisherRepository;

@Service
public class PublisherService {
	@Autowired
	private PublisherRepository publisherRepository;



public List<Publisher> getAllPublisher() {
	// TODO Auto-generated method stub
	return publisherRepository.findAll();
}
}
