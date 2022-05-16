package com.wfhackathon2022.speakaboos.io.model;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class SavePronunciationInformationRequest {

	@NotNull
	@Getter @Setter
	@JsonProperty("employeeId")
	private int employeeId;
	
	@NotNull
	@Getter @Setter
	@JsonProperty("optOutFlag")
	private boolean optOutFlag;
	
	@Getter @Setter
	@JsonProperty("locale")
	private String locale;
	
	@Getter @Setter
    @DecimalMin(value = "1.0", inclusive = true)
	@DecimalMax(value = "3.0", inclusive = true)
    @Digits(integer=1, fraction=1)
	@JsonProperty("speed")
	private BigDecimal speed;	
	
}
