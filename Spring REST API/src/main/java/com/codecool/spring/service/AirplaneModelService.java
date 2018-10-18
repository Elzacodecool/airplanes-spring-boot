package com.codecool.spring.service;

import com.codecool.spring.exception.AirplaneModelNotFoundException;
import com.codecool.spring.exception.AirplaneModelWrongDataException;
import com.codecool.spring.model.AirplaneModel;
import com.codecool.spring.model.Producer;
import com.codecool.spring.repository.AirplaneModelRepository;
import com.codecool.spring.repository.ProducerRepository;
import org.hibernate.exception.JDBCConnectionException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AirplaneModelService {

    @Autowired
    private AirplaneModelRepository airplaneModelRepository;

    @Autowired
    private ProducerRepository producerRepository;

    public List<AirplaneModel> getAllAirplaneModels() throws AirplaneModelNotFoundException,
            JDBCConnectionException {

        try {
            List<AirplaneModel> output = airplaneModelRepository.findAllByIsArchivedIsFalse();
            if (!output.isEmpty()) return output;
            throw new AirplaneModelNotFoundException("No airplane models in database");
        }
        catch (DataAccessResourceFailureException e) {
            throw new JDBCConnectionException("Connection to database failed", new SQLException());
        }
    }

    public List<AirplaneModel> getAllAirplaneModels(long producerId) throws AirplaneModelNotFoundException,
            JDBCConnectionException {

        try {
            List<AirplaneModel> output = airplaneModelRepository.findAllByProducerIdAndIsArchivedIsFalse(producerId);
            if (!output.isEmpty()) return output;
            throw new AirplaneModelNotFoundException("No airplane models for producer with id: " + producerId);
        }
        catch (DataAccessResourceFailureException e) {
            throw new JDBCConnectionException("Connection to database failed", new SQLException());
        }
    }

    public AirplaneModel getAirplaneModel(long id) throws AirplaneModelNotFoundException {
        AirplaneModel output = airplaneModelRepository.findByIdAndIsArchivedIsFalse(id);
        if (output == null) {
            throw new AirplaneModelNotFoundException("No airplane model for id: " + id);
        }
        return output;
    }

    public void addAirplaneModel(String airplaneModelJSON) throws AirplaneModelWrongDataException {
        try {
            JSONObject jsonObject = new JSONObject(airplaneModelJSON);
            Producer producer = producerRepository.findByIdAndIsArchivedIsFalse(jsonObject.getLong("producer"));
            AirplaneModel airplaneModel = new AirplaneModel(jsonObject.getString("modelName"),
                    jsonObject.getInt("maxSeat"));

            airplaneModel.setProducer(producer);
            airplaneModelRepository.save(airplaneModel);
        }
        catch (JSONException e) {
            throw new AirplaneModelWrongDataException("Failed to add airplane model");
        }

    }

    public void addAirplaneModel(long producerId, AirplaneModel airplaneModel)
            throws AirplaneModelWrongDataException {
        Producer producer = producerRepository.findByIdAndIsArchivedIsFalse(producerId);
        if (producer == null) {
            throw new AirplaneModelWrongDataException("Failed to add airplane model. " +
                    "Wrong producer id: " + producerId);
        }
        airplaneModel.setProducer(producer);
        airplaneModelRepository.save(airplaneModel);
    }

    public void updateAirplaneModel(long producerId, long id, AirplaneModel updateAirplaneModel)
            throws AirplaneModelWrongDataException {

        Producer producer = producerRepository.findByIdAndIsArchivedIsFalse(producerId);

        if (producer == null) {
            throw new AirplaneModelWrongDataException("Failed to update airplane model. " +
                    "Wrong producer id: " + producerId);
        }

        AirplaneModel airplaneModel = airplaneModelRepository.findByIdAndIsArchivedIsFalse(id);
        
        airplaneModel.setModelName(updateAirplaneModel.getModelName());
        airplaneModel.setMaxSeat(updateAirplaneModel.getMaxSeat());
        airplaneModel.setProducer(producer);
        
        airplaneModelRepository.save(airplaneModel);
    }

    public void updateAirplaneModel(long id, String airplaneModel) throws AirplaneModelWrongDataException {
        try {
            JSONObject jsonObject = new JSONObject(airplaneModel);
            Producer producer =  producerRepository.findByIdAndIsArchivedIsFalse(jsonObject.getLong("producer"));

            AirplaneModel updatedModel = airplaneModelRepository.findByIdAndIsArchivedIsFalse(id);

            updatedModel.setModelName(jsonObject.getString("modelName"));
            updatedModel.setMaxSeat(jsonObject.getInt("maxSeat"));
            updatedModel.setProducer(producer);

            airplaneModelRepository.save(updatedModel);
        }
        catch (JSONException e) {
            throw new AirplaneModelWrongDataException("Failed to update airplane model with id: " + id);
        }
    }

    public void deleteAirplaneModel(long id) throws AirplaneModelWrongDataException {
    	AirplaneModel airplaneModel = airplaneModelRepository.findByIdAndIsArchivedIsFalse(id);
    	if (airplaneModel == null) {
            throw new AirplaneModelWrongDataException("Failed to delete airplane model with id: " + id);
        }
    	airplaneModel.setArchived(true);
    	airplaneModelRepository.save(airplaneModel);
    }
}
