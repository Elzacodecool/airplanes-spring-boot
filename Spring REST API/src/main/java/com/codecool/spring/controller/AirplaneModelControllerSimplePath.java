package com.codecool.spring.controller;

import com.codecool.spring.model.AirplaneModel;
import com.codecool.spring.service.AirplaneModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AirplaneModelControllerSimplePath {

    @Autowired
    private AirplaneModelService airplaneModelService;

}
