package com.codecool.spring.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "airplane_models")
public class AirplaneModel {

	@Id
	@GeneratedValue
	@Column(name = "model_id")
	private long id;
	@Column(name = "model_name")
	private String modelName;
	@Column(name = "maxSeat")
	private int maxSeat;

	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private Producer producer;
	
	private boolean isArchived;

	public AirplaneModel() {
	}

	public AirplaneModel(String modelName, int maxSeat) {
		this.modelName = modelName;
		this.maxSeat = maxSeat;
		this.producer = new Producer();
	}

	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public int getMaxSeat() {
		return maxSeat;
	}

	public void setMaxSeat(int maxSeat) {
		this.maxSeat = maxSeat;
	}

	public boolean isArchived() {
		return isArchived;
	}

	public void setArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}

	public long getId() {
		return id;
	}
}