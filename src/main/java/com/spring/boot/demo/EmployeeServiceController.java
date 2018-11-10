package com.spring.boot.demo;

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

import com.spring.boot.demo.domain.Employee;
import com.spring.boot.demo.repository.EmployeeRepository;

@Controller
public class EmployeeServiceController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
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
		return ResponseEntity.ok(savedEmployee);

	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable long id) {

		Optional<Employee> employeeOptional = employeeRepository.findById(id);

		if (!employeeOptional.isPresent())
			return ResponseEntity.notFound().build();

		employee.setEmployeeId(id);
		Employee savedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(savedEmployee);
	}
}
