package com.ox.bank.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Officer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;

	String name;
	String level;

	/*
	 * @OneToMany(mappedBy = "officer", cascade = CascadeType.REMOVE) private
	 * Set<Loan> Loans;
	 */

	public Officer() {
		super();
	}
	public Officer(long id) {
		super();
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}



}
