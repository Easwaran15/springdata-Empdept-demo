package com.sample.employee.demo.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sample.employee.demo.model.EmployeeBackup;

@Repository
public interface EmployeeBackupRestRepository extends CrudRepository<EmployeeBackup, String>{
}
