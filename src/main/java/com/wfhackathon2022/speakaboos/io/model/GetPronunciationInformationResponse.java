package com.wfhackathon2022.speakaboos.io.model;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

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
    @DecimalMin(value = "1.0", inclusive = true)
	@DecimalMax(value = "3.0", inclusive = true)
    @Digits(integer=1, fraction=1)
	@JsonProperty("speed")	
	private BigDecimal speed;
	
}
