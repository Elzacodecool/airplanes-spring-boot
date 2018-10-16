package com.codecool.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Producer {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	@OneToMany
	private List<AirplaneModel> models = new ArrayList<>();
	private String owner;
	
	public Producer() {
		
	}
	
	public Producer(String name, String owner) {
		this.name = name;
		this.owner = owner;
	}
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<AirplaneModel> getModels() {
		return models;
	}
	public void setModels(List<AirplaneModel> models) {
		this.models = models;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
		
}
