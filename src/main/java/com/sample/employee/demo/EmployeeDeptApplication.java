package com.sample.employee.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sample.employee.demo.repository.EmployeeDataRestRepository;

@SpringBootApplication
public class EmployeeDeptApplication {

	@Autowired
	EmployeeDataRestRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeDeptApplication.class, args);
	}
}
