package com.sample.employee.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.employee.demo.model.Employee;
import com.sample.employee.demo.model.EmployeeBackup;

@Configuration
@EnableAsync
@EnableScheduling
public class EmployeeBackupJob {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeBackupServiceImpl employeeBackupService;

	@Scheduled(fixedDelay = 60000, initialDelay = 10000)
	public void fixedDelaySch() {
		System.out.println(" Employee backup job initiated");
		List<Employee> empList = employeeService.getAllEmployees();
		if(!CollectionUtils.isEmpty(empList)) {
			List<EmployeeBackup> empBkpList = empList.stream().map(x -> {
				EmployeeBackup employeeBkp = new EmployeeBackup();
				ObjectMapper jsonMapper = new ObjectMapper() .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				try {
					employeeBkp = jsonMapper.readValue(jsonMapper.writeValueAsString(x), EmployeeBackup.class);
					employeeBkp.setDeptId(x.getDepartment().getDeptId());
				} catch (Exception e) {
					e.printStackTrace();
				} 
				return employeeBkp;
			}).collect(Collectors.toList());
			
			try {
				System.out.println(" Backing up below employees = "+new ObjectMapper().writeValueAsString(empBkpList));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			employeeBackupService.saveAllEmployeesBkp(empBkpList);
		}
		System.out.println(" Employee backup job completed");
	}
}
