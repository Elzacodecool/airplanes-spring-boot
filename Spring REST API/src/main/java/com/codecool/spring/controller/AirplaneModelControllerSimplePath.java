package com.codecool.spring.controller;

import com.codecool.spring.exception.AirplaneModelNotFoundException;
import com.codecool.spring.exception.AirplaneModelWrongDataException;
import com.codecool.spring.model.AirplaneModel;
import com.codecool.spring.service.AirplaneModelService;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AirplaneModelControllerSimplePath {

    @Autowired
    private AirplaneModelService airplaneModelService;

    @GetMapping("/airplanes")
    public List<AirplaneModel> getAllAirplaneModels() throws AirplaneModelNotFoundException,
            JDBCConnectionException {
        return airplaneModelService.getAllAirplaneModels();
    }

    @GetMapping("/airplanes/{id}")
    public AirplaneModel getAirplaneModel(@PathVariable long id) throws AirplaneModelNotFoundException,
            JDBCConnectionException {
        return airplaneModelService.getAirplaneModel(id);
    }

    @PostMapping("/airplanes")
    public void addAirplaneModel(@RequestBody String airplaneModel) throws AirplaneModelWrongDataException,
            JDBCConnectionException {
        airplaneModelService.addAirplaneModel(airplaneModel);
    }

    @PutMapping("/airplanes/{id}")
    public void updateAirplaneModel(@PathVariable long id, @RequestBody String airplaneModel)
            throws AirplaneModelWrongDataException, JDBCConnectionException {
        airplaneModelService.updateAirplaneModel(id, airplaneModel);
    }

    @DeleteMapping("/airplanes/{id}")
    public void deleteAirplaneModel(@PathVariable long id) throws AirplaneModelWrongDataException,
            JDBCConnectionException {
        airplaneModelService.deleteAirplaneModel(id);
    }
}
