package com.wfhackathon2022.speakaboos.exception;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.wfhackathon2022.speakaboos.io.model.StatusMessage;
import com.wfhackathon2022.speakaboos.io.model.StatusMessageResponse;
import com.wfhackathon2022.speakaboos.io.model.SystemError;
import com.wfhackathon2022.speakaboos.io.model.SystemErrorResponse;

@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler{

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CommonExceptionHandler.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(RuntimeException ex, WebRequest request){
		SystemErrorResponse response = new SystemErrorResponse();
		SystemError error = new SystemError();
		error.setCode("WFH1000");
		error.setMessage(ex.getMessage());
		response.getSystemErrors().add(error);
		LOG.error("Unhandled exception occured: "+ex.getMessage(), ex);
		return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(PronunciationException.class)
	public final ResponseEntity<Object> handlePronunciationException(PronunciationException ex){
		SystemErrorResponse response = new SystemErrorResponse();
		SystemError error = new SystemError();
		error.setCode("WFH2000");
		error.setMessage(ex.getMessage());
		response.getSystemErrors().add(error);
		LOG.error("Pronunciation exception occured: "+ex.getMessage(), ex);
		return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public final ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
		StatusMessageResponse response = new StatusMessageResponse();
		StatusMessage statusMessage = new StatusMessage();
		StringBuilder message = new StringBuilder();
		ex.getBindingResult().getFieldErrors().stream().forEach(fe -> message.append(fe.getDefaultMessage()));
		statusMessage.setType(StatusMessage.TypeEnum.ERROR);
		statusMessage.setCode("WFH3000");
		statusMessage.setMessage(message.toString());
		response.getStatusMessages().add(statusMessage);
		LOG.error("Bad Request: "+ex.getMessage(), ex);
		return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
		
	}
		
}
