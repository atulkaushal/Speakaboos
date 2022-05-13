package com.wfhackathon2022.speakaboos.io.model;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Validated
@EqualsAndHashCode
public class SystemError {

	@JsonProperty("code")
	@Getter @Setter
	private String code = null;
	
	@JsonProperty("message")
	@Getter @Setter
	private String message = null;
}
