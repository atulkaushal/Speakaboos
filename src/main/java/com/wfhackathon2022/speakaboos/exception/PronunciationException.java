package com.wfhackathon2022.speakaboos.exception;

import lombok.Getter;

public class PronunciationException extends RuntimeException{

	private static final long serialVersionUID = -3358775557467965707L;
	
	@Getter
	private String errorCode;
	
	public PronunciationException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public PronunciationException(String message, String errorCode, Throwable t) {
		super(message, t);
		this.errorCode = errorCode;
	}
}
