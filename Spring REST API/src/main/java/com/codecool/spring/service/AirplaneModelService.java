package com.codecool.spring.service;

import com.codecool.spring.model.AirplaneModel;
import com.codecool.spring.model.Producer;
import com.codecool.spring.repository.AirplaneModelRepository;
import com.codecool.spring.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirplaneModelService {

    @Autowired
    private AirplaneModelRepository airplaneModelRepository;

    @Autowired
    private ProducerRepository producerRepository;

    public List<AirplaneModel> getAllAirplaneModels(long producerId) {
        List<AirplaneModel> models = new ArrayList<>();
        airplaneModelRepository.findByProducerId(producerId).forEach(models::add);
        return models;
    }

    public AirplaneModel getAirplaneModel(long id) {
        return airplaneModelRepository.findById(id).get();
    }

    public void addAirplaneModel(long producerId, AirplaneModel airplaneModel) {
        Producer producer = producerRepository.findById(producerId).get();
        airplaneModel.setProducer(producer);
        airplaneModelRepository.save(airplaneModel);
    }
}
