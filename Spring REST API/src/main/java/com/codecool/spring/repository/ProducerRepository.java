package com.codecool.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.codecool.spring.model.Producer;

@Repository
public interface ProducerRepository extends CrudRepository<Producer, Long> {
	List<Producer> findAllByIsArchived(boolean isArchived);
	Producer findByIdAndIsArchived(long id, boolean isArchived);
}
