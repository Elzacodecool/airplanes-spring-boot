package com.codecool.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codecool.spring.model.Producent;
import com.codecool.spring.repository.ProducentRepository;

@Service
public class ProducentService {

	@Autowired
	private ProducentRepository producentRepository;
	
	public ProducentService(ProducentRepository pr) {
		this.producentRepository = pr;
	}
	
	public void createProducent(String name, String owner) {
		this.producentRepository.save(new Producent(name, owner));
	}
}
