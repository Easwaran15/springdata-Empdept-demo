package com.sample.employee.demo.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sample.employee.demo.model.EmployeeBackup;

@Component
public interface EmployeeBackupService {
	
		public void saveAllEmployeesBkp(List<EmployeeBackup> employeeBkpList);

		public List<EmployeeBackup> getAllEmployeesBkp();
		
	}
