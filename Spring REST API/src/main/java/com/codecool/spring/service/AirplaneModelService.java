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
        LOGGER.info("Get all airplane models");
        return airplaneModelRepository.findAllByIsArchivedIsFalse();
    }

    public List<AirplaneModel> getAllAirplaneModels(long producerId) {
        LOGGER.info("Get all airplane models by producer id: " + producerId);
        return airplaneModelRepository.findAllByProducerIdAndIsArchivedIsFalse(producerId);
    }

    public AirplaneModel getAirplaneModel(long id) {
        return airplaneModelRepository.findByIdAndIsArchivedIsFalse(id);
    }

    public void addAirplaneModel(String airplaneModelJSON) {
        JSONObject jsonObject = new JSONObject(airplaneModelJSON);
        Producer producer = producerRepository.findByIdAndIsArchivedIsFalse(jsonObject.getLong("producer"));

        AirplaneModel airplaneModel = new AirplaneModel(jsonObject.getString("modelName"),
                jsonObject.getInt("maxSeat"));

        airplaneModel.setProducer(producer);
        airplaneModelRepository.save(airplaneModel);
    }

    public void addAirplaneModel(long producerId, AirplaneModel airplaneModel) {
        Producer producer = producerRepository.findByIdAndIsArchivedIsFalse(producerId);
        airplaneModel.setProducer(producer);
        airplaneModelRepository.save(airplaneModel);
    }

    public void updateAirplaneModel(long producerId, long id, AirplaneModel updateAirplaneModel) {
        Producer producer;
        if (updateAirplaneModel.getProducer() == null) producer = producerRepository.findByIdAndIsArchivedIsFalse(producerId);
        else producer = updateAirplaneModel.getProducer();

        AirplaneModel airplaneModel = airplaneModelRepository.findByIdAndIsArchivedIsFalse(id);
        
        airplaneModel.setModelName(updateAirplaneModel.getModelName());
        airplaneModel.setMaxSeat(updateAirplaneModel.getMaxSeat());
        airplaneModel.setProducer(producer);
        
        airplaneModelRepository.save(airplaneModel);
    }

    public void updateAirplaneModel(long id, String airplaneModel) {
        JSONObject jsonObject = new JSONObject(airplaneModel);
        Producer producer =  producerRepository.findByIdAndIsArchivedIsFalse(jsonObject.getLong("producer"));

        AirplaneModel updatedModel = airplaneModelRepository.findByIdAndIsArchivedIsFalse(id);

        updatedModel.setModelName(jsonObject.getString("modelName"));
        updatedModel.setMaxSeat(jsonObject.getInt("maxSeat"));
        updatedModel.setProducer(producer);

        airplaneModelRepository.save(updatedModel);
    }

    public void deleteAirplaneModel(long id) {
    	AirplaneModel airplaneModel = airplaneModelRepository.findByIdAndIsArchivedIsFalse(id);
    	airplaneModel.setArchived(true);
    	airplaneModelRepository.save(airplaneModel);
    }
}
