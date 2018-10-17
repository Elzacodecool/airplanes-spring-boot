package com.codecool.spring.repository;

import com.codecool.spring.model.AirplaneModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AirplaneModelRepository extends JpaRepository<AirplaneModel, Long> {
	List<AirplaneModel> findAllByIsArchivedIsFalse();
	AirplaneModel findByIdAndIsArchivedIsFalse(long id);
    List<AirplaneModel> findAllByProducerIdAndIsArchivedIsFalse(long id);
}