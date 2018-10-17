package com.codecool.spring.service;

import java.util.ArrayList;
import java.util.List;

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
		return producerRepository.findByIdAndIsArchivedIsFalse(id);
	}
	
	public void add(Producer producer) {
		producerRepository.save(producer);
	}

	public void update(long id, Producer producerDetails) {
		Producer currnetProducer = producerRepository.findByIdAndIsArchivedIsFalse(id);
		currnetProducer.setName(producerDetails.getName());
		currnetProducer.setOwner(producerDetails.getOwner());
		producerRepository.save(currnetProducer);
	}
	
	public void deleteProducer(long id) {
		Producer producer = producerRepository.findByIdAndIsArchivedIsFalse(id);
		producer.setArchived(true);
		for(AirplaneModel airplaneModel : producer.getModels()) {
			airplaneModel.setArchived(true);
			producer.getModels().remove(airplaneModel);
		}
		producerRepository.save(producer);
	}
	
}
