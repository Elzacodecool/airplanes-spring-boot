package com.codecool.spring.controller;

import com.codecool.spring.model.AirplaneModel;
import com.codecool.spring.service.AirplaneModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AirplaneModelController {

    @Autowired
    private AirplaneModelService airplaneModelService;

    @GetMapping("/producers/{id}/airplanes")
    public List<AirplaneModel> getAllAirplaneModels(@PathVariable long id) {
        return airplaneModelService.getAllAirplaneModels(id);
    }

    @GetMapping("/producers/{producerId}/airplanes/{id}")
    public AirplaneModel getAirplaneModel(@PathVariable long id) {
        return airplaneModelService.getAirplaneModel(id);
    }

    @PostMapping("/producers/{producerId}/airplanes")
    public void addAirplaneModel(@RequestBody AirplaneModel airplaneModel, @PathVariable long producerId) {
        airplaneModelService.addAirplaneModel(producerId, airplaneModel);
    }
}