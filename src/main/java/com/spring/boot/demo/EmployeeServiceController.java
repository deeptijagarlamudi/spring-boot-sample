package com.spring.boot.demo;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.boot.demo.domain.Employee;
import com.spring.boot.demo.repository.EmployeeRepository;

@Controller
public class EmployeeServiceController {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping(value ="/employees", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employee>> retrieveAllEmployees() {
		return ResponseEntity.ok(employeeRepository.findAll());
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> retrieveEmployee(@PathVariable long id) {
		Optional<Employee> Employee = employeeRepository.findById(id);
		return ResponseEntity.ok(Employee.get());
	}

	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable long id) {
		employeeRepository.deleteById(id);
	}

	@PostMapping("/employees")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee Employee) {
		Employee savedEmployee = employeeRepository.save(Employee);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedEmployee.getEmployeeId()).toUri();
		return ResponseEntity.ok(savedEmployee);

	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee Employee, @PathVariable long id) {

		Optional<Employee> EmployeeOptional = employeeRepository.findById(id);

		if (!EmployeeOptional.isPresent())
			return ResponseEntity.notFound().build();

		Employee.setEmployeeId(id);
		
		employeeRepository.save(Employee);

		return ResponseEntity.noContent().build();
	}
}
