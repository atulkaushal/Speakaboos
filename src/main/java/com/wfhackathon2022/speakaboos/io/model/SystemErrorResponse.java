package com.wfhackathon2022.speakaboos.io.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Validated
@EqualsAndHashCode
public class SystemErrorResponse {

	@Valid
	@JsonProperty("systemErrors")
	@Getter @Setter
	private List<SystemError> systemErrors = new ArrayList<>();
}
