package com.codecool.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.codecool.spring.model.Producer;

@Repository
public interface ProducerRepository extends CrudRepository<Producer, Long> {

}
