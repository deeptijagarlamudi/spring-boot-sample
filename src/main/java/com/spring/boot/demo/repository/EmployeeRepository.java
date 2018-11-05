package com.spring.boot.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.demo.domain.Employee;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long> {

}
