package com.wfhackathon2022.speakaboos.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public enum SpeechLanguage {

	ZH_HK("zh-HK","Chinese (Cantonese, Traditional)","zh-HK-HiuGaaiNeural"),
	ZH_CN("zh-CN","Chinese (Mandarin, Simplified)","zh-CN-XiaohanNeural"),
	ZH_TW("zh-TW","Chinese (Taiwanese Mandarin)","zh-TW-HsiaoChenNeural"),
	NL_BE("nl-BE","Dutch (Belgium)","nl-BE-DenaNeural"),
	NL_NL("nl-NL","Dutch (Netherlands)","nl-NL-ColetteNeural"),
	EN_AU("en-AU","English (Australia)","en-AU-NatashaNeural"),
	EN_CA("en-CA","English (Canada)","en-CA-ClaraNeural"),
	EN_HK("en-HK","English (Hongkong)","en-HK-YanNeural"),
	EN_IN("en-IN","English (India)","en-IN-NeerjaNeural"),
	EN_IE("en-IE","English (Ireland)","en-IE-EmilyNeural"),
	EN_KE("en-KE","English (Kenya)","en-KE-AsiliaNeural"),
	EN_NZ("en-NZ","English (New Zealand)","en-NZ-MollyNeural"),
	EN_NG("en-NG","English (Nigeria)","en-NG-EzinneNeural"),
	EN_PH("en-PH","English (Philippines)","en-PH-RosaNeural"),
	EN_SG("en-SG","English (Singapore)","en-SG-LunaNeural"),
	EN_ZA("en-ZA","English (South Africa)","en-ZA-LeahNeural"),
	EN_TZ("en-TZ","English (Tanzania)","en-TZ-ImaniNeural"),
	EN_GB("en-GB","English (United Kingdom)","en-GB-LibbyNeural"),
	EN_US("en-US","English (United States)","en-US-AmberNeural"),
	FI_FI("fi-FI","Finnish (Finland)","fi-FI-NooraNeural"),
	FR_BE("fr-BE","French (Belgium)","fr-BE-CharlineNeural"),
	FR_CA("fr-CA","French (Canada)","fr-CA-SylvieNeural"),
	FR_FR("fr-FR","French (France)","fr-FR-DeniseNeural"),
	FR_CH("fr-CH","French (Switzerland)","fr-CH-ArianeNeural"),
	DE_AT("de-AT","German (Austria)","de-AT-IngridNeural"),
	DE_DE("de-DE","German (Germany)","de-DE-KatjaNeural"),
	DE_CH("de-CH","German (Switzerland)","de-CH-LeniNeural"),
	EL_GR("el-GR","Greek (Greece)","el-GR-AthinaNeural"),
	HI_IN("hi-IN","Hindi (India)","hi-IN-SwaraNeural"),
	IS_IS("is-IS","Icelandic (Iceland)","is-IS-GudrunNeural"),
	GA_IE("ga-IE","Irish (Ireland)","ga-IE-OrlaNeural"),
	IT_IT("it-IT","Italian (Italy)","it-IT-ElsaNeural"),
	ES_GT("es-GT","Spanish (Guatemala)","es-GT-MartaNeural"),
	ES_MX("es-MX","Spanish (Mexico)","es-MX-DaliaNeural"),
	ES_PR("es-PR","Spanish (Puerto Rico)","es-PR-KarinaNeural"),
	ES_ES("es-ES","Spanish (Spain)","es-ES-ElviraNeural"),
	ES_US("es-US","Spanish (US)","es-US-PalomaNeural"),
	SV_SE("sv-SE","Swedish (Sweden)","sv-SE-HilleviNeural"),
	TA_IN("ta-IN","Tamil (India)","ta-IN-PallaviNeural"),
	TE_IN("te-IN","Telugu (India)","te-IN-ShrutiNeural");
	
	@Getter
	private String value;
	
	@Getter
	private String description;
	
	@Getter
	private String voice;
	
	private SpeechLanguage(String value, String description, String voice) {
		this.value = value;
		this.description = description;
		this.voice = voice;
	}
	
	public static boolean isValidLanguage(String language) {
		return speechLanguageMap.containsKey(language);
	}
	
	public static String getVoice(String locale) {
		return speechLanguageMap.get(locale) != null ? speechLanguageMap.get(locale).getVoice() : null;
	}
	
	private static Map<String, SpeechLanguage> speechLanguageMap = new HashMap<>();
	
	static {
		for(SpeechLanguage speechLanguage : SpeechLanguage.values()) {
			speechLanguageMap.put(speechLanguage.getValue(), speechLanguage);
		}
	}
}
