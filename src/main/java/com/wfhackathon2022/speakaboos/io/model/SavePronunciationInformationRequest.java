package com.wfhackathon2022.speakaboos.io.model;

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
	private Boolean optOutFlag;
	
	@Getter @Setter
	@JsonProperty("locale")
	private String locale;
	
	@Getter @Setter
	@JsonProperty("speed")
	private Integer speed;	
	
}
