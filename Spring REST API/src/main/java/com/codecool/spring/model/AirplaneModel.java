package com.codecool.spring.model;

import javax.persistence.*;

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

	@ManyToOne
	private Producer producer;

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

	public long getId() {
		return id;
	}
}