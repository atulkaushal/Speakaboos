package com.wfhackathon2022.speakaboos.delegate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wfhackathon2022.speakaboos.dao.PronunciationDAO;
import com.wfhackathon2022.speakaboos.entity.Employee;
import com.wfhackathon2022.speakaboos.exception.PronunciationException;
import com.wfhackathon2022.speakaboos.io.model.EmployeeDetails;
import com.wfhackathon2022.speakaboos.io.model.GetEmployeeDetailsRequest;
import com.wfhackathon2022.speakaboos.io.model.GetEmployeeDetailsResponse;
import com.wfhackathon2022.speakaboos.io.model.ListEmployeesResponse;

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
	
	public ListEmployeesResponse listEmployees() {
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
			return response;
		}
}
