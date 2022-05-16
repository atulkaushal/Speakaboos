package com.wfhackathon2022.speakaboos.controller;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.wfhackathon2022.speakaboos.delegate.PronunciationDelegate;
import com.wfhackathon2022.speakaboos.io.model.GetEmployeeDetailsRequest;
import com.wfhackathon2022.speakaboos.io.model.GetEmployeeDetailsResponse;
import com.wfhackathon2022.speakaboos.io.model.GetPronunciationInformationRequest;
import com.wfhackathon2022.speakaboos.io.model.GetPronunciationInformationResponse;
import com.wfhackathon2022.speakaboos.io.model.ListEmployeesResponse;
import com.wfhackathon2022.speakaboos.io.model.SavePronunciationInformationRequest;
import com.wfhackathon2022.speakaboos.io.model.StatusMessageResponse;

@Controller
public class PronunciationController {
	
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PronunciationController.class);
	
	@Autowired
	private PronunciationDelegate pronunciationDelegate;
	
	@RequestMapping(value = "/V1/getPronunciationInformation/V1", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE } )
	public ResponseEntity<GetPronunciationInformationResponse> getPronunciationInformation(
			@Valid @RequestBody GetPronunciationInformationRequest request){
		LOG.info("PronunciationController::getPronunciationInformation::begin");
		GetPronunciationInformationResponse response = pronunciationDelegate.getPronunciationInformation(request);
		LOG.info("PronunciationController::getPronunciationInformation::end");
		return new ResponseEntity<GetPronunciationInformationResponse>(response, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/V1/getPronunciationInformation/V1", method = RequestMethod.POST, produces = { MediaType.APPLICATION_OCTET_STREAM_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE } )
	public ResponseEntity<ByteArrayResource> getPronunciationAudio(
			@Valid @RequestBody GetPronunciationInformationRequest request){
		LOG.info("PronunciationController::getPronunciationAudio::begin");
		byte[] audio = pronunciationDelegate.getPronunciationAudio(request);
		LOG.info("PronunciationController::getPronunciationAudio::end");
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(new ByteArrayResource(audio));
		
	}
	
	@RequestMapping(value = "/V1/savePronunciationInformation/V1", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE } )
	public ResponseEntity<StatusMessageResponse> savePronunciationInformation(
			@Valid @RequestBody SavePronunciationInformationRequest savePronunciationInformationRequest){
		LOG.info("PronunciationController::savePronunciationInformation::begin");
		StatusMessageResponse response = pronunciationDelegate.savePronunciationInformation(savePronunciationInformationRequest);
		LOG.info("PronunciationController::savePronunciationInformation::end");
		return new ResponseEntity<StatusMessageResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/V1/savePronunciationInformation/V1", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE } )
	public ResponseEntity<StatusMessageResponse> savePronunciationAudio(
			@RequestHeader(value = "EmployeeId", required = true) Integer employeeId,
			@RequestParam("file") MultipartFile nameAudio){
		LOG.info("PronunciationController::savePronunciationAudio::begin");
		StatusMessageResponse response = pronunciationDelegate.savePronunciationAudio(employeeId, nameAudio);
		LOG.info("PronunciationController::savePronunciationAudio::end");
		return new ResponseEntity<StatusMessageResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/V1/getEmployeeDetails/V1", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE } )
	public ResponseEntity<GetEmployeeDetailsResponse> getEmployeeDetails(
			@Valid @RequestBody GetEmployeeDetailsRequest request){
		LOG.info("PronunciationController::getEmployeeDetails::begin");
		GetEmployeeDetailsResponse response =  pronunciationDelegate.getEmployeeDetails(request);
		LOG.info("PronunciationController::getEmployeeDetails::end");
		return new ResponseEntity<GetEmployeeDetailsResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/V1/listEmployees/V1", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE } )
	public ResponseEntity<ListEmployeesResponse> listEmployees(){
		LOG.info("PronunciationController::listEmployees::begin");
		ListEmployeesResponse response =  pronunciationDelegate.listEmployees();
		LOG.info("PronunciationController::listEmployees::end");
		return new ResponseEntity<ListEmployeesResponse>(response, HttpStatus.OK);
		
	}

}
