package com.nexential.springBoot.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstname;
	private String lastname;
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Vehicles> vehicals;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public List<Vehicles> getVehicals() {
		return vehicals;
	}
	public void setVehicals(List<Vehicles> vehicals) {
		this.vehicals = vehicals;
	}
	public User(int id, String firstname, String lastname, List<Vehicles> vehicals) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.vehicals = vehicals;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
