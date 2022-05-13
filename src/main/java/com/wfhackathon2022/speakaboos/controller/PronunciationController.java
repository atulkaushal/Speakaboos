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

import com.wfhackathon2022.speakaboos.io.model.GetEmployeeDetailsRequest;
import com.wfhackathon2022.speakaboos.io.model.GetEmployeeDetailsResponse;
import com.wfhackathon2022.speakaboos.io.model.ListEmployeesResponse;

@Controller
public class PronunciationController {
	
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PronunciationController.class);
	
	
	@RequestMapping(value = "/V1/getPronunciationInformation/V1", method = RequestMethod.POST, produces = { "multipart/form-data" }, consumes = { "application/json" } )
	public ResponseEntity<MultiValueMap<String, Object>> getPronunciationInformation(
			@Valid @RequestBody String request, HttpServletRequest req, HttpServletResponse res){
		
		return null;
	}
	
	@RequestMapping(value = "/V1/savePronunciationInformation/V1", method = RequestMethod.POST, produces = { "application/json" }, consumes = { "multipart/form-data" } )
	public ResponseEntity<MultiValueMap<String, Object>> savePronunciationInformation(
			@Valid @RequestBody String request, HttpServletRequest req, HttpServletResponse res){
		
		return null;
	}
	
	@RequestMapping(value = "/V1/getEmployeeDetails/V1", method = RequestMethod.POST, produces = { "application/json" }, consumes = { "application/json" } )
	public ResponseEntity<GetEmployeeDetailsResponse> getEmployeeDetails(
			@Valid @RequestBody GetEmployeeDetailsRequest request){
		
		return null;
	}
	
	@RequestMapping(value = "/V1/listEmployees/V1", method = RequestMethod.POST, produces = { "application/json" }, consumes = { "application/json" } )
	public ResponseEntity<ListEmployeesResponse> listEmployees(){
		
		return null;
	}

}
