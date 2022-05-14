package com.wfhackathon2022.speakaboos.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.wfhackathon2022.speakaboos.delegate.PronunciationDelegate;
import com.wfhackathon2022.speakaboos.io.model.GetEmployeeDetailsRequest;
import com.wfhackathon2022.speakaboos.io.model.GetEmployeeDetailsResponse;
import com.wfhackathon2022.speakaboos.io.model.GetPronunciationInformationRequest;
import com.wfhackathon2022.speakaboos.io.model.ListEmployeesResponse;
import com.wfhackathon2022.speakaboos.io.model.SavePronunciationInformationRequest;
import com.wfhackathon2022.speakaboos.io.model.StatusMessageResponse;

@Controller
public class PronunciationController {
	
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PronunciationController.class);
	
	@Autowired
	private PronunciationDelegate pronunciationDelegate;
	
	@RequestMapping(value = "/V1/getPronunciationInformation/V1", method = RequestMethod.POST, produces = { "multipart/form-data" }, consumes = { "application/json" } )
	public ResponseEntity<MultiValueMap<String, Object>> getPronunciationInformation(
			@Valid @RequestBody GetPronunciationInformationRequest request, HttpServletRequest req, HttpServletResponse res){
		LOG.info("PronunciationController::getPronunciationInformation::begin");
		MultiValueMap<String, Object>  map = pronunciationDelegate.getPronunciationInformation(request);
		LOG.info("PronunciationController::getPronunciationInformation::end");
		return (ResponseEntity<MultiValueMap<String, Object>>) ResponseEntity.ok().contentType(MediaType.MULTIPART_FORM_DATA).body(map);
		
	}
	
	@RequestMapping(value = "/V1/savePronunciationInformation/V1", method = RequestMethod.POST, produces = { "application/json" }, consumes = { "multipart/form-data" } )
	public ResponseEntity<StatusMessageResponse> savePronunciationInformation(
			@Valid @RequestPart("savePronunciationInformationRequest") SavePronunciationInformationRequest savePronunciationInformationRequest,
			@Valid @RequestPart(value = "nameaudio.mp3", required = false) MultipartFile nameAudio){
		LOG.info("PronunciationController::savePronunciationInformation::begin");
		StatusMessageResponse response = pronunciationDelegate.savePronunciationInformation(savePronunciationInformationRequest, nameAudio);
		LOG.info("PronunciationController::savePronunciationInformation::end");
		return new ResponseEntity<StatusMessageResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/V1/getEmployeeDetails/V1", method = RequestMethod.POST, produces = { "application/json" }, consumes = { "application/json" } )
	public ResponseEntity<GetEmployeeDetailsResponse> getEmployeeDetails(
			@Valid @RequestBody GetEmployeeDetailsRequest request){
		LOG.info("PronunciationController::getEmployeeDetails::begin");
		GetEmployeeDetailsResponse response =  pronunciationDelegate.getEmployeeDetails(request);
		LOG.info("PronunciationController::getEmployeeDetails::end");
		return new ResponseEntity<GetEmployeeDetailsResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/V1/listEmployees/V1", method = RequestMethod.POST, produces = { "application/json" } )
	public ResponseEntity<ListEmployeesResponse> listEmployees(){
		LOG.info("PronunciationController::listEmployees::begin");
		ListEmployeesResponse response =  pronunciationDelegate.listEmployees();
		LOG.info("PronunciationController::listEmployees::end");
		return new ResponseEntity<ListEmployeesResponse>(response, HttpStatus.OK);
		
	}

}
