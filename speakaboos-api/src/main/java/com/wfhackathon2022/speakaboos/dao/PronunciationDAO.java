package com.wfhackathon2022.speakaboos.dao;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wfhackathon2022.speakaboos.entity.Employee;
import com.wfhackathon2022.speakaboos.entity.PronunciationPreferences;
import com.wfhackathon2022.speakaboos.repository.EmployeeRepository;
import com.wfhackathon2022.speakaboos.repository.PronunciationPreferencesRepository;

@Repository
public class PronunciationDAO {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PronunciationDAO.class);
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private PronunciationPreferencesRepository pronunciationPreferencesRepository;
	
	public Optional<Employee> getEmployeeDetails(Integer employeeId) {
		LOG.info("PronunciationDAO::getEmployeeDetails::begin");
		Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
		LOG.info("PronunciationDAO::getEmployeeDetails::end");
		return optionalEmployee;
	}
	
	public List<Employee> getEmployeeList() {
		LOG.info("PronunciationDAO::getEmployeeList::begin");
		List<Employee> employeeList = employeeRepository.findAll();
		LOG.info("PronunciationDAO::getEmployeeList::end");
		return employeeList;
	}
	
	public void savePronunciationInformation(PronunciationPreferences preference) {
		LOG.info("PronunciationDAO::savePronunciationInformation::begin");
		pronunciationPreferencesRepository.save(preference);
		LOG.info("PronunciationDAO::savePronunciationInformation::end");
	}

	public Optional<PronunciationPreferences> getPronunciationPreferences(Integer employeeId){
		LOG.info("PronunciationDAO::getPronunciationPreferences::begin");
		Optional<PronunciationPreferences> optionalPronunciationPreferences = pronunciationPreferencesRepository.findById(employeeId);
		LOG.info("PronunciationDAO::getPronunciationPreferences::end");
		return optionalPronunciationPreferences;
	}
		
}
