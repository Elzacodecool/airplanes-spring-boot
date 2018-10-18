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
public class AirplaneModelController {

    @Autowired
    private AirplaneModelService airplaneModelService;

    @GetMapping("/producers/{id}/airplanes")
    public List<AirplaneModel> getAllAirplaneModels(@PathVariable long id) throws AirplaneModelNotFoundException,
            JDBCConnectionException {
        return airplaneModelService.getAllAirplaneModels(id);
    }

    @GetMapping("/producers/{producerId}/airplanes/{id}")
    public AirplaneModel getAirplaneModel(@PathVariable long id) throws AirplaneModelNotFoundException,
            JDBCConnectionException {
        return airplaneModelService.getAirplaneModel(id);
    }

    @PostMapping("/producers/{producerId}/airplanes")
    public void addAirplaneModel(@RequestBody AirplaneModel airplaneModel, @PathVariable long producerId)
            throws AirplaneModelWrongDataException, JDBCConnectionException {
        airplaneModelService.addAirplaneModel(producerId, airplaneModel);
    }

    @PutMapping("/producers/{producerId}/airplanes/{id}")
    public void updateAirplaneModel(@RequestBody AirplaneModel airplaneModel,
                                    @PathVariable long producerId, @PathVariable long id)
            throws AirplaneModelWrongDataException, JDBCConnectionException {
        airplaneModelService.updateAirplaneModel(producerId, id, airplaneModel);
    }

    @DeleteMapping("/producers/{producerId}/airplanes/{id}")
    public void deleteAirplaneModel(@PathVariable long id) throws AirplaneModelWrongDataException,
            JDBCConnectionException {
        airplaneModelService.deleteAirplaneModel(id);
    }
}