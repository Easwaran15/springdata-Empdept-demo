package com.sample.employee.demo.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sample.employee.demo.model.EmployeeBackup;
import com.sample.employee.demo.repository.EmployeeBackupRestRepository;

@Service
public class EmployeeBackupServiceImpl implements EmployeeBackupService {

	@Autowired
	private EmployeeBackupRestRepository employeeBackupRestRepository;


	@Transactional(readOnly = true)
	public List<EmployeeBackup> getAllEmployeesBkp() {
		List<EmployeeBackup> employeeBkpResponse = (List<EmployeeBackup>) employeeBackupRestRepository.findAll();
		System.out.println(" Employees archieved Successfully");
		return employeeBkpResponse;
	}

	@Override
	public void saveAllEmployeesBkp(List<EmployeeBackup> employeeBkpList) {
		employeeBackupRestRepository.saveAll(employeeBkpList);
	}

}