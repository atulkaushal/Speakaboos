package com.wfhackathon2022.speakaboos.io.model;

import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Validated
@EqualsAndHashCode
public class GetPronunciationInformationResponse {

	@Getter @Setter
	@JsonProperty("language")	
	private String language;
	
	@Getter @Setter
	@Range(min = 1, max = 3)
	@JsonProperty("speed")	
	private Integer speed;
	
}
