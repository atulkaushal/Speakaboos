package com.wfhackathon2022.speakaboos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
}
