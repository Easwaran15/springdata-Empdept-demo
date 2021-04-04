package com.sample.employee.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sample.employee.demo.model.Employee;

@Repository
public interface EmployeeDataRestRepository extends CrudRepository<Employee, String>{
	public List<Employee> findByName(String name);
	public Long deleteByName(String name);
}
