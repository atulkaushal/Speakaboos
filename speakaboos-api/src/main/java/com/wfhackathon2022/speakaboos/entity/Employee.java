package com.wfhackathon2022.speakaboos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema="dbo", name = "Employee")
public class Employee {
	
	@Id
	@Getter @Setter
	@Column(name = "EmployeeID", nullable=false)
	private Integer employeeId;
	
	@Getter @Setter
	@Column(name = "LegalFirstName")
	private String legalFirstName;
	
	@Getter @Setter
	@Column(name = "LegalLastName")
	private String legalLastName;
	
	@Getter @Setter
	@Column(name = "PreferredName")
	private String preferredName;
	

}
