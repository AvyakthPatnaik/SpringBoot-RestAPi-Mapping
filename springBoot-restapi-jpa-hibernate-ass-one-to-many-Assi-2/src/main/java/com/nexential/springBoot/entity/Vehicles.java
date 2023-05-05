package com.nexential.springBoot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="vehical")
public class Vehicles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int year;
	private String name;
	private String model;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Vehicles(int id, int year, String name, String model) {
		super();
		this.id = id;
		this.year = year;
		this.name = name;
		this.model = model;
	}
	public Vehicles() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
