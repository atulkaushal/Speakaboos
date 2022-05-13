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
	
	@Getter @Setter
	@Column(name="preference")
	private String preference;
	
	
	public enum Preference {
		STANDARD("standard"), CUSTOM("custom");
		
		private String value;
		
		Preference (String value) {
			this.value = value;
		}
		
		public static Preference parse(String preference) {
			Preference pref = null;
			for (Preference item : Preference.values()) {
				if(item.value.equalsIgnoreCase(preference)) {
					pref = item;
					break;
				}
			}
			return pref;
		}
	}
	
	public Preference getPreferece() {
		return Preference.parse(this.preference);
	}
	
	public void setPreference(Preference preference) {
		this.preference = preference.value;
	}
}
