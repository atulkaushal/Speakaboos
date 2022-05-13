package com.wfhackathon2022.speakaboos.io.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Validated
@EqualsAndHashCode
public class GetPronunciationInformationRequest {

	@NotNull
	@Range(min = 1)
	@Getter @Setter
	@JsonProperty("employeeId")
	private Integer employeeId;
	
	@NotNull
	@Getter @Setter
	@JsonProperty("name")	
	private String name;
	
	@Getter @Setter
	@JsonProperty("language")	
	private String language;
	
	@Getter @Setter
	@Range(min = 1, max = 3)
	@JsonProperty("speed")	
	private Integer speed;
}
