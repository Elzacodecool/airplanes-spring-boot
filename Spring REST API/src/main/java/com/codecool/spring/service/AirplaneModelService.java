package com.codecool.spring.service;

import com.codecool.spring.model.AirplaneModel;
import com.codecool.spring.model.Producer;
import com.codecool.spring.repository.AirplaneModelRepository;
import com.codecool.spring.repository.ProducerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirplaneModelService {

    private static final Logger LOGGER = LogManager.getLogger(AirplaneModelService.class.getName());

    @Autowired
    private AirplaneModelRepository airplaneModelRepository;

    @Autowired
    private ProducerRepository producerRepository;

    public List<AirplaneModel> getAllAirplaneModels() {
        List<AirplaneModel> models = new ArrayList<>();
        airplaneModelRepository.findAll().forEach(models::add);
        return models;
    }

    public List<AirplaneModel> getAllAirplaneModels(long producerId) {
        List<AirplaneModel> models = new ArrayList<>();
        airplaneModelRepository.findByProducerId(producerId).forEach(models::add);
        return models;
    }

    public AirplaneModel getAirplaneModel(long id) {
        return airplaneModelRepository.findById(id).get();
    }

    public void addAirplaneModel(String airplaneModelJSON) {
        JSONObject jsonObject = new JSONObject(airplaneModelJSON);
        Producer producer = producerRepository.findById(jsonObject.getLong("producer")).get();

        AirplaneModel airplaneModel = new AirplaneModel(jsonObject.getString("modelName"),
                jsonObject.getInt("maxSeat"));

        airplaneModel.setProducer(producer);
        airplaneModelRepository.save(airplaneModel);
    }

    public void addAirplaneModel(long producerId, AirplaneModel airplaneModel) {
        Producer producer = producerRepository.findById(producerId).get();
        airplaneModel.setProducer(producer);
        airplaneModelRepository.save(airplaneModel);
    }

    public void updateAirplaneModel(long producerId, long id, AirplaneModel updateAirplaneModel) {
        Producer producer;
        if (updateAirplaneModel.getProducer() == null) producer = producerRepository.findById(producerId).get();
        else producer = updateAirplaneModel.getProducer();

        airplaneModelRepository.findById(id).map(airplaneModel -> {
            airplaneModel.setModelName(updateAirplaneModel.getModelName());
            airplaneModel.setMaxSeat(updateAirplaneModel.getMaxSeat());
            airplaneModel.setProducer(producer);
            return airplaneModelRepository.save(airplaneModel);
        });
    }

    public void deleteAirplaneModel(long id) {
        airplaneModelRepository.deleteById(id);
    }
}
