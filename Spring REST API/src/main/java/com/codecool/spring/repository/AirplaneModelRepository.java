package com.codecool.spring.repository;

import com.codecool.spring.model.AirplaneModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AirplaneModelRepository extends JpaRepository<AirplaneModel, Long> {

    public List<AirplaneModel> findByProducerId(long id);
}