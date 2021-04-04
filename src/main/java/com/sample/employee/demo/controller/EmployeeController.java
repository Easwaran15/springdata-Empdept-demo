package com.sample.employee.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.employee.demo.model.Employee;
import com.sample.employee.demo.model.EmployeeBackup;
import com.sample.employee.demo.service.EmployeeBackupService;
import com.sample.employee.demo.service.EmployeeService;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeBackupService employeeBkpService;
	
	@RequestMapping(value = "/getemployee", method = RequestMethod.GET)
	public Employee findById(@RequestBody Employee employee) {
		return employeeService.get(employee);
	}
	
	@RequestMapping(value = "/deleteemployee", method = RequestMethod.DELETE)
	public String delete(@RequestBody Employee employee) {
		return employeeService.deleteEmployee(employee);
	}

	@RequestMapping(value = "/updateemployee", method = RequestMethod.PUT)

	public String update(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);
	}

	@RequestMapping(value = "/getallemployees", method = RequestMethod.GET)
	public List<Employee> getAllStudents() {
		List<Employee> empRespList = (List<Employee>) employeeService.getAllEmployees();
		return empRespList;
	}

	@RequestMapping(value = "/saveemployee", method = RequestMethod.POST)
	public String saveEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}

	@RequestMapping(value = "/getbkpemployees", method = RequestMethod.GET)
	public List<EmployeeBackup> getBkpEmployees() {
		return employeeBkpService.getAllEmployeesBkp();
	}
}