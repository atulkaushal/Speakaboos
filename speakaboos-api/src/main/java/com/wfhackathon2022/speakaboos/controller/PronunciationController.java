package com.wfhackathon2022.speakaboos.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PronunciationController {
	
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PronunciationController.class);
	
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = { "multipart/form-data" }, consumes = { "application/json" } )
	public ResponseEntity<MultiValueMap<String, Object>> getPronunciationInformation(
			@Valid @RequestBody String request, HttpServletRequest req, HttpServletResponse res){
		
		return null;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = { "application/json" }, consumes = { "multipart/form-data" } )
	public ResponseEntity<MultiValueMap<String, Object>> savePronunciationInformation(
			@Valid @RequestBody String request, HttpServletRequest req, HttpServletResponse res){
		
		return null;
	}

}
