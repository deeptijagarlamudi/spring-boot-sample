package com.spring.boot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // same as @Configuration+@EnableAutoConfiguration+@ComponentScan
public class EmployeeServiceServer {
	public static void main(String args[]) {
		SpringApplication.run(EmployeeServiceServer.class, args);
	}

}
