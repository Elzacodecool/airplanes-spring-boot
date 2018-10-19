package com.codecool.spring.controller;

import java.util.List;

import com.codecool.spring.exception.ProducerNotFoundException;
import com.codecool.spring.exception.ProducerWrongDataException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codecool.spring.model.Producer;
import com.codecool.spring.service.ProducerService;

@RestController
public class ProducerController {
	
	@Autowired
	private ProducerService producerService;

	@GetMapping("/producers")
	public List<Producer> getAllProducers() {
		return producerService.getAllProducers();
	}
	
	@GetMapping("/producers/{id}")
	public Producer getProducer(@PathVariable long id) {
		return producerService.getProducer(id);
	}

	@PostMapping("/producers")
	public void addProducer(@RequestBody Producer producer) {
		producerService.add(producer);
	}
	
	@PutMapping("/producers/{id}")
	public void updateProducer(@RequestBody Producer producer, @PathVariable long id) {
		producerService.update(id, producer);
	}
	
	@DeleteMapping("/producers/{id}")
	public void deleteProducer(@PathVariable long id) {
		producerService.deleteProducer(id);
	}
}
