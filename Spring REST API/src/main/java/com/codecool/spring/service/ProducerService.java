package com.codecool.spring.service;

import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.codecool.spring.exception.ProducerNotFoundException;
import com.codecool.spring.exception.ProducerWrongDataException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import com.codecool.spring.model.AirplaneModel;
import com.codecool.spring.model.Producer;
import com.codecool.spring.repository.ProducerRepository;

@Service
public class ProducerService {

	private final ProducerRepository producerRepository;

    @Autowired
    public ProducerService(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

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
        if (producer.getName() != null && producer.getOwner() != null) {
            producerRepository.save(producer);
        } else {
            throw new ProducerWrongDataException();
        }
	}

	public void update(long id, Producer producerDetails) {
        Producer currentProducer = producerRepository.findByIdAndIsArchivedIsFalse(id);
        if (currentProducer == null) {
            throw new ProducerNotFoundException(id);
        }
        currentProducer.setName(producerDetails.getName());
        currentProducer.setOwner(producerDetails.getOwner());
        producerRepository.save(currentProducer);
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
