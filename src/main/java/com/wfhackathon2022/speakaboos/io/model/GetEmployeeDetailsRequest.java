package com.wfhackathon2022.speakaboos.io.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class GetEmployeeDetailsRequest {

	@NotNull
	@Range(min = 1)
	@Getter @Setter
	@JsonProperty("employeeId")
	private Integer employeeId;
}
