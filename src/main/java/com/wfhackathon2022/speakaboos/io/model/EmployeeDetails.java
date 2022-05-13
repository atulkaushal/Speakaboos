package com.wfhackathon2022.speakaboos.io.model;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class EmployeeDetails {

	@Getter @Setter
	@JsonProperty("employeeId")
	private Integer employeeId;
	
	@Getter @Setter
	@Column(name = "legalFirstName")
	private String legalFirstName;
	
	@Getter @Setter
	@Column(name = "legalLastName")
	private String legalLastName;
	
	@Getter @Setter
	@Column(name = "preferredName")
	private String preferredName;
	
}
