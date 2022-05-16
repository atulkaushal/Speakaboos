package com.wfhackathon2022.speakaboos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wfhackathon2022.speakaboos.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
