package com.codecool.spring.service;

import java.util.List;

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
	
	public List<Producer> getAllProducers() {
		return (List<Producer>) producerRepository.findAll();
	}
	
	public Producer getProducer(long id) {
		return producerRepository.findById(id).get();
	}
	
	public void add(Producer producer) {
		producerRepository.save(producer);
	}

	public void update(long id, Producer producerDetails) {
		Producer currnetProducer = producerRepository.findById(id).get();
		currnetProducer.setName(producerDetails.getName());
		currnetProducer.setOwner(producerDetails.getOwner());
		producerRepository.save(currnetProducer);
	}
	
}
