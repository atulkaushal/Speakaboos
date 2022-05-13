package com.wfhackathon2022.speakaboos.io.model;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Validated
@EqualsAndHashCode
public class ListEmployeesResponse {

	@Valid
	@JsonProperty("employeeDetailsList")
	@Getter @Setter
	private List<EmployeeDetails> employeeDetailsList = null;
	
	
}
