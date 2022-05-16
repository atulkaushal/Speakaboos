package com.wfhackathon2022.speakaboos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.wfhackathon2022.speakaboos.entity.Employee;
import com.wfhackathon2022.speakaboos.entity.PronunciationPreferences;
import com.wfhackathon2022.speakaboos.io.model.GetEmployeeDetailsRequest;
import com.wfhackathon2022.speakaboos.io.model.GetPronunciationInformationRequest;
import com.wfhackathon2022.speakaboos.io.model.SavePronunciationInformationRequest;
import com.wfhackathon2022.speakaboos.io.model.StatusMessage;
import com.wfhackathon2022.speakaboos.io.model.StatusMessageResponse;

public class TestsCommonHelper {

	public GetEmployeeDetailsRequest createGetEmployeeDetailsRequest() {
		GetEmployeeDetailsRequest request = new GetEmployeeDetailsRequest();
		request.setEmployeeId(123);
		return request;
	}

	public Employee createEmployee(Integer id, String... names) {
		Employee emp = new Employee();
		emp.setEmployeeId(id);
		if(names.length >= 1)
		emp.setLegalFirstName(names[0]);
		if(names.length >= 2)
		emp.setLegalLastName(names[1]);
		if(names.length >= 3)
		emp.setPreferredName(names[2]);
		return emp;
	}

	public List<Employee> createEmployeeList() {
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(this.createEmployee(4, "Baohua", "Lee"));
		return employees;
	}

	public PronunciationPreferences createPronunciationPreferences() {
		PronunciationPreferences pronunciationPreferences = new PronunciationPreferences();
		pronunciationPreferences.setEmployeeId(1);
		pronunciationPreferences.setLocale("en-IN");
		pronunciationPreferences.setOptOutFlag(true);
		pronunciationPreferences.setSpeed(new BigDecimal(5));
		return pronunciationPreferences;
	}

	public SavePronunciationInformationRequest createSavePronunciationInformationRequest() {
		SavePronunciationInformationRequest savePronunciationInformationRequest = new SavePronunciationInformationRequest();
		savePronunciationInformationRequest.setEmployeeId(1);
		savePronunciationInformationRequest.setLocale("en-IN");
		savePronunciationInformationRequest.setOptOutFlag(true);
		savePronunciationInformationRequest.setSpeed(new BigDecimal(5));
		return savePronunciationInformationRequest;
	}

	public StatusMessageResponse createStatusMessageResponse(String msg) {
		StatusMessageResponse statusMessageResponse = new StatusMessageResponse();
		StatusMessage statusMessage = new StatusMessage();
		statusMessage.setMessage(msg);
		List<StatusMessage> statusMessageList = new ArrayList<StatusMessage>();
		statusMessageList.add(statusMessage);
		statusMessageResponse.setStatusMessages(statusMessageList);
		return statusMessageResponse;
	}

	public GetPronunciationInformationRequest createGetPronunciationInformationRequest() {
		GetPronunciationInformationRequest request = new GetPronunciationInformationRequest();
		request.setEmployeeId(1);
		request.setName("rama");
		request.setLanguage("en-IN");
		request.setSpeed(new BigDecimal(5));
		return request;
	}
}
