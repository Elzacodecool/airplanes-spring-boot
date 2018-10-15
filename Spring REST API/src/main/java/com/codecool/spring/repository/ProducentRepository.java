package com.codecool.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.codecool.spring.model.Producent;

@Repository
public interface ProducentRepository extends CrudRepository<Producent, Long> {

}
