package com.wfhackathon2022.speakaboos.io.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class EmployeeDetails {

	@Getter @Setter
	@JsonProperty("employeeId")
	private Integer employeeId;
	
	@Getter @Setter
	private String legalFirstName;
	
	@Getter @Setter
	private String legalLastName;
	
	@Getter @Setter
	private String preferredName;
	
}
