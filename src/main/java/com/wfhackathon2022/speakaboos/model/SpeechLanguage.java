package com.wfhackathon2022.speakaboos.model;

import lombok.Getter;

public enum SpeechLanguage {

	ZH_HK("zh-HK","Chinese (Cantonese, Traditional)"),
	ZH_CN("zh-CN","Chinese (Mandarin, Simplified)"),
	ZH_TW("zh-TW","Chinese (Taiwanese Mandarin)"),
	NL_BE("nl-BE","Dutch (Belgium)"),
	NL_NL("nl-NL","Dutch (Netherlands)"),
	EN_AU("en-AU","English (Australia)"),
	EN_CA("en-CA","English (Canada)"),
	EN_HK("en-HK","English (Hongkong)"),
	EN_IN("en-IN","English (India)"),
	EN_IE("en-IE","English (Ireland)"),
	EN_KE("en-KE","English (Kenya)"),
	EN_NZ("en-NZ","English (New Zealand)"),
	EN_NG("en-NG","English (Nigeria)"),
	EN_PH("en-PH","English (Philippines)"),
	EN_SG("en-SG","English (Singapore)"),
	EN_ZA("en-ZA","English (South Africa)"),
	EN_TZ("en-TZ","English (Tanzania)"),
	EN_GB("en-GB","English (United Kingdom)"),
	EN_US("en-US","English (United States)"),
	FI_FI("fi-FI","Finnish (Finland)"),
	FR_BE("fr-BE","French (Belgium)"),
	FR_CA("fr-CA","French (Canada)"),
	FR_FR("fr-FR","French (France)"),
	FR_CH("fr-CH","French (Switzerland)"),
	DE_AT("de-AT","German (Austria)"),
	DE_DE("de-DE","German (Germany)"),
	DE_CH("de-CH","German (Switzerland)"),
	EL_GR("el-GR","Greek (Greece)"),
	HI_IN("hi-IN","Hindi (India)"),
	IS_IS("is-IS","Icelandic (Iceland)"),
	GA_IE("ga-IE","Irish (Ireland)"),
	IT_IT("it-IT","Italian (Italy)"),
	ES_GT("es-GT","Spanish (Guatemala)"),
	ES_MX("es-MX","Spanish (Mexico)"),
	ES_PR("es-PR","Spanish (Puerto Rico)"),
	ES_ES("es-ES","Spanish (Spain)"),
	ES_US("es-US","Spanish (US)"),
	SV_SE("sv-SE","Swedish (Sweden)"),
	TA_IN("ta-IN","Tamil (India)"),
	TE_IN("te-IN","Telugu (India)");
	
	@Getter
	private String value;
	
	@Getter
	private String description;
	
	private SpeechLanguage(String value, String description) {
		this.value = value;
		this.description = description;
	}
}
