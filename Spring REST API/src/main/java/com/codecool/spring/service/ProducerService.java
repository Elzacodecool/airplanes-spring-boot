package com.codecool.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codecool.spring.model.Producer;
import com.codecool.spring.repository.ProducerRepository;

@Service
public class ProducerService {

	@Autowired
	private ProducerRepository producerRepository;
	
	public ProducerService(ProducerRepository pr) {
		this.producerRepository = pr;
	}
	
	public void createProducent(String name, String owner) {
		this.producerRepository.save(new Producer(name, owner));
	}
}
