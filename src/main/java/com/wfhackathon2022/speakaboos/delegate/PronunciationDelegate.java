package com.wfhackathon2022.speakaboos.delegate;

import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wfhackathon2022.speakaboos.dao.PronunciationDAO;
import com.wfhackathon2022.speakaboos.entity.Employee;
import com.wfhackathon2022.speakaboos.exception.PronunciationException;
import com.wfhackathon2022.speakaboos.io.model.GetEmployeeDetailsRequest;
import com.wfhackathon2022.speakaboos.io.model.GetEmployeeDetailsResponse;

@Component
public class PronunciationDelegate {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PronunciationDelegate.class);

	@Autowired
	private PronunciationDAO pronunciationDAO;
	
	
	public GetEmployeeDetailsResponse getEmployeeDetails(GetEmployeeDetailsRequest request){
		LOG.info("PronunciationDelegate::getEmployeeDetails::begin");
		Optional<Employee> optionalEmployee = pronunciationDAO.getEmployeeDetails(request.getEmployeeId());
		if(optionalEmployee.isEmpty()) {
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
}
