package com.wfhackathon2022.speakaboos.delegate;

import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wfhackathon2022.speakaboos.dao.PronunciationDAO;
import com.wfhackathon2022.speakaboos.entity.Employee;
import com.wfhackathon2022.speakaboos.io.model.GetEmployeeDetailsRequest;
import com.wfhackathon2022.speakaboos.io.model.GetEmployeeDetailsResponse;

@Component
public class PronunciationDelegate {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PronunciationDelegate.class);

	@Autowired
	private PronunciationDAO pronunciationDAO;
	
	
	public GetEmployeeDetailsResponse getEmployeeDetails(GetEmployeeDetailsRequest request){
		
		Optional<Employee> optionalEmployee = pronunciationDAO.getEmployeeDetails(request.getEmployeeId());
		if(optionalEmployee.isEmpty()) {
			
		}
		
		return null;
	}
}
