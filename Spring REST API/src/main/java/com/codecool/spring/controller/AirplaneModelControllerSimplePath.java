package com.codecool.spring.controller;

import com.codecool.spring.exception.AirplaneModelNotFoundException;
import com.codecool.spring.model.AirplaneModel;
import com.codecool.spring.service.AirplaneModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AirplaneModelControllerSimplePath {

    @Autowired
    private AirplaneModelService airplaneModelService;

    @GetMapping("/airplanes")
    public List<AirplaneModel> getAllAirplaneModels() throws AirplaneModelNotFoundException {
        return airplaneModelService.getAllAirplaneModels();
    }

    @GetMapping("/airplanes/{id}")
    public AirplaneModel getAirplaneModel(@PathVariable long id) {
        return airplaneModelService.getAirplaneModel(id);
    }

    @PostMapping("/airplanes")
    public void addAirplaneModel(@RequestBody String airplaneModel) {
        airplaneModelService.addAirplaneModel(airplaneModel);
    }

    @PutMapping("/airplanes/{id}")
    public void updateAirplaneModel(@PathVariable long id, @RequestBody String airplaneModel) {
        airplaneModelService.updateAirplaneModel(id, airplaneModel);
    }

    @DeleteMapping("/airplanes/{id}")
    public void deleteAirplaneModel(@PathVariable long id) {
        airplaneModelService.deleteAirplaneModel(id);
    }
}
