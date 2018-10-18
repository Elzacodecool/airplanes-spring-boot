package com.codecool.spring.service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import com.codecool.spring.exception.ProducerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codecool.spring.model.AirplaneModel;
import com.codecool.spring.model.Producer;
import com.codecool.spring.repository.ProducerRepository;

@Service
public class ProducerService {

	@Autowired
	private ProducerRepository producerRepository;
	
	public List<Producer> getAllProducers() {
		List<Producer> producers = producerRepository.findAllByIsArchivedIsFalse();
		for (Producer producer: producers) {
			List<AirplaneModel> models = new ArrayList<>();
			for(AirplaneModel airplaneModel: producer.getModels()) {
				if(!airplaneModel.isArchived()) {
					models.add(airplaneModel);
				}
			}
			producer.setModels(models);
		}
		
		return producers;
	}
	
	public Producer getProducer(long id) {
		Producer producer = producerRepository.findByIdAndIsArchivedIsFalse(id);
		if (producer == null) {
			throw new ProducerNotFoundException(id);
		}
		List<AirplaneModel> models = new ArrayList<>();
		for(AirplaneModel airplaneModel: producer.getModels()) {
			if(!airplaneModel.isArchived()) {
				models.add(airplaneModel);
			}
		}
		producer.setModels(models);
		return producer;
	}
	
	public void add(Producer producer) {
		producerRepository.save(producer);
	}

	public void update(long id, Producer producerDetails) {
		Producer currnetProducer = producerRepository.findByIdAndIsArchivedIsFalse(id);
		if (currnetProducer == null) {
		    throw new ProducerNotFoundException(id);
        }
		currnetProducer.setName(producerDetails.getName());
		currnetProducer.setOwner(producerDetails.getOwner());
		producerRepository.save(currnetProducer);
	}
	
	public void deleteProducer(long id) {
		Producer producer = producerRepository.findByIdAndIsArchivedIsFalse(id);
		if (producer == null) {
		    throw new ProducerNotFoundException(id);
        }
		producer.setArchived(true);
		for(AirplaneModel airplaneModel : producer.getModels()) {
			airplaneModel.setArchived(true);
			producer.getModels().remove(airplaneModel);
		}
		producerRepository.save(producer);
	}
	
}
