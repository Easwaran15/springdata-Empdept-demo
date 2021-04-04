package com.sample.employee.demo.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sample.employee.demo.model.Employee;

@Component
public interface EmployeeService {
	
		public List<Employee> saveAllEmployees(List<Employee> employeeList);

		public String saveEmployee(Employee employee);
		
		public Employee get(Employee employee);
		
		public List<Employee> getAllEmployees();
		
		public String updateEmployee(Employee emp);

		public String deleteEmployee(Employee employee);

		public String deleteAllPassingEntities(List<Employee> employee);

		public String deleteAll();

	}
