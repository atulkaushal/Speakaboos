package com.wfhackathon2022.speakaboos.delegate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import com.wfhackathon2022.speakaboos.dao.PronunciationDAO;
import com.wfhackathon2022.speakaboos.entity.Employee;
import com.wfhackathon2022.speakaboos.entity.PronunciationPreferences;
import com.wfhackathon2022.speakaboos.exception.PronunciationException;
import com.wfhackathon2022.speakaboos.helper.Helper;
import com.wfhackathon2022.speakaboos.io.model.EmployeeDetails;
import com.wfhackathon2022.speakaboos.io.model.GetEmployeeDetailsRequest;
import com.wfhackathon2022.speakaboos.io.model.GetEmployeeDetailsResponse;
import com.wfhackathon2022.speakaboos.io.model.GetPronunciationInformationRequest;
import com.wfhackathon2022.speakaboos.io.model.GetPronunciationInformationResponse;
import com.wfhackathon2022.speakaboos.io.model.ListEmployeesResponse;
import com.wfhackathon2022.speakaboos.io.model.SavePronunciationInformationRequest;
import com.wfhackathon2022.speakaboos.io.model.StatusMessageResponse;
import com.wfhackathon2022.speakaboos.service.AzureCognitiveServie;

@Component
public class PronunciationDelegate {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PronunciationDelegate.class);

	@Autowired
	private PronunciationDAO pronunciationDAO;
	
	@Autowired
	private Helper helper;
	
	@Autowired
	private AzureCognitiveServie azureCognitiveServie;

	public GetEmployeeDetailsResponse getEmployeeDetails(GetEmployeeDetailsRequest request) {
		LOG.info("PronunciationDelegate::getEmployeeDetails::begin");
		Optional<Employee> optionalEmployee = pronunciationDAO.getEmployeeDetails(request.getEmployeeId());
		if (optionalEmployee.isEmpty()) {
			throw new PronunciationException("No employee found", "WFH9001");
		}
		Employee employee = optionalEmployee.get();
		GetEmployeeDetailsResponse response = new GetEmployeeDetailsResponse();
		response.setEmployeeId(employee.getEmployeeId());
		response.setLegalFirstName(employee.getLegalFirstName());
		response.setLegalLastName(employee.getLegalLastName());
		response.setPreferredName(employee.getPreferredName());
		LOG.info("PronunciationDelegate::getEmployeeDetails::end");
		return response;
	}

	public ListEmployeesResponse listEmployees() {
		LOG.info("PronunciationDelegate::listEmployees::begin");
		ListEmployeesResponse response = new ListEmployeesResponse();
		List<Employee> employees = pronunciationDAO.getEmployeeList();
		List<EmployeeDetails> employeeDetails = new ArrayList<EmployeeDetails>();
		if(!employees.isEmpty()) {
		 employeeDetails = employees.stream()
				.map(e -> {
				EmployeeDetails empDetails = new EmployeeDetails();
				empDetails.setEmployeeId(e.getEmployeeId());
				empDetails.setLegalFirstName(e.getLegalFirstName());
				empDetails.setLegalLastName(e.getLegalLastName());
				return empDetails;
				}).collect(Collectors.toList());
		response.setEmployeeDetailsList(employeeDetails);
		}			
		LOG.info("PronunciationDelegate::listEmployees::end");
		return response;
	}

	@Transactional
	public StatusMessageResponse savePronunciationInformation(SavePronunciationInformationRequest request, MultipartFile nameAudio) {
		LOG.info("PronunciationDelegate::savePronunciationInformation::begin");
		PronunciationPreferences preference = null;
		Optional<PronunciationPreferences> optionalPronunciationPreferences = pronunciationDAO
				.getPronunciationPreferences(request.getEmployeeId());
		
		if(optionalPronunciationPreferences.isEmpty()) {
			preference = new PronunciationPreferences();
			preference.setEmployeeId(request.getEmployeeId());
			
		} else {
			preference = optionalPronunciationPreferences.get();
		}
		preference.setOptOutFlag(request.getOptOutFlag());
		if(request.getOptOutFlag()) {
			preference.setLocale(null);
			preference.setSpeed(null);
			preference.setAudio(null);
		} else {
			preference.setLocale(request.getLocale());
			preference.setSpeed(request.getSpeed());
			try {
				preference.setAudio(nameAudio.getBytes());
			} catch (IOException e) {
				LOG.error("ronunciationDelegate::savePronunciationInformation::exception: "+e.getMessage(), e);
				throw new PronunciationException("Unable to extract file: "+e.getMessage(), "WFH9003", e);
			}
		}
		pronunciationDAO.savePronunciationInformation(preference);
		LOG.info("PronunciationDelegate::savePronunciationInformation::end");
		return helper.createStatusMessageResponse("Employee preference saved successfully");
	}

	public MultiValueMap<String, Object> getPronunciationInformation(GetPronunciationInformationRequest request) {
		LOG.info("PronunciationDelegate::getPronunciationInformation::begin");
		Optional<PronunciationPreferences> optionalPronunciationPreferences = pronunciationDAO
				.getPronunciationPreferences(request.getEmployeeId());
		String language = "en-US";
		Integer speed = Integer.valueOf(1);
		if (optionalPronunciationPreferences.isPresent()) {
			PronunciationPreferences pronunciationPreferences = optionalPronunciationPreferences.get();
			language = request.getLanguage() != null ? request.getLanguage()
					: pronunciationPreferences.getLocale() != null ? pronunciationPreferences.getLocale() : language;
			speed = request.getSpeed() != null ? request.getSpeed()
					: pronunciationPreferences.getSpeed() != null ? pronunciationPreferences.getSpeed() : speed;
		} else {
			language = request.getLanguage() != null ? request.getLanguage() : language;
			speed = request.getSpeed() != null ? request.getSpeed() : speed;
		}
		byte[] audio = azureCognitiveServie.retrieveSpeech(request.getName(), language,
				speed);

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

		map.add("nameaudio.mp3", audio);

		GetPronunciationInformationResponse response = new GetPronunciationInformationResponse();
		response.setLanguage(language);
		response.setSpeed(speed);

		map.add("preferences", response);

		LOG.info("PronunciationDelegate::getPronunciationInformation::end");
		return map;
	}
}
