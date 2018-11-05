package com.spring.boot.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.demo.domain.Employee;
import com.spring.boot.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	
	public Employee inserEmployee(Employee e) {
		return employeeRepository.save(e);
		
	}
	
	public Optional<Employee> getEmployee(Long employeeId) {
		return employeeRepository.findById(employeeId);
	}

}
