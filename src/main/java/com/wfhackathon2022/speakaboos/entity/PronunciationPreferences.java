package com.wfhackathon2022.speakaboos.entity;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema="dbo", name = "PronunciationPreferences")
@DynamicUpdate
public class PronunciationPreferences {

	@Id
	@Getter @Setter
	@Column(name = "EmployeeID", nullable=false)
	private Integer employeeId;
	
	@Getter @Setter
	@Column(name = "OptOutFlag")
	private Boolean optOutFlag;
	
	@Getter @Setter
	@Column(name = "Locale")
	private String locale;
	
	@Getter @Setter
	@Column(name = "Speed")
	private BigDecimal speed;
	
	@Getter @Setter
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "Audio")
	private byte[] audio;
}
