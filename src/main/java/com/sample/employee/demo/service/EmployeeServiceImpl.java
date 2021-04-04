package com.sample.employee.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.employee.demo.model.Dept;
import com.sample.employee.demo.model.Employee;
import com.sample.employee.demo.repository.EmployeeDataRestRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDataRestRepository employeeRepository;

	@Transactional
	public List<Employee> saveAllEmployees(List<Employee> studentList) {
		List<Employee> response = (List<Employee>) employeeRepository.saveAll(studentList);
		return response;
	}

	@Transactional(readOnly = true)
	public List<Employee> getAllEmployees() {
		List<Employee> studentResponse = (List<Employee>) employeeRepository.findAll();
		return studentResponse;
	}
	
	@Transactional
	public String deleteAllPassingEntities(List<Employee> employees) {
		String result = null;
		try {
		employeeRepository.deleteAll(employees);
		result = " Employees deleted successfully";
		} catch (Exception e) {
			e.printStackTrace();
			result = "Failure: Unexpeted error";
		}
		return result;
	}
	
	@Transactional
	public String updateEmployee(Employee employee) {
			Employee dbEmployee = get(employee);
			ObjectMapper mapper = null;
			if(dbEmployee != null) {
				String id = dbEmployee.getId();
				Dept department = dbEmployee.getDepartment();
				mapper = new ObjectMapper();
				try {
					dbEmployee = mapper.readValue(mapper.writeValueAsString(employee), Employee.class);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
					return "Failure: Unexpeted error";
				}
				dbEmployee.setId(id);
				dbEmployee.setDepartment(department);
				employeeRepository.save(dbEmployee);
				return "Success : Employee with id : "+dbEmployee.getId()+ " updated";
			}else {
				return " Failure - Unable to find Employee";
			}
			
	}
	
	
	@Transactional
	public String deleteEmployee(Employee employee) {
		String result = null;
		
		if(employee.getId()!=null) {
			try {
			employeeRepository.delete(employee);
			result = " Employee with id:"+employee.getId()+" deleted successfully";
			}
			catch(Exception e) {
				e.printStackTrace();
				result = "Unable to delete Employee with id:"+employee.getId();
			}
		}else if(employee.getName()!=null) {
			long noOfRecords = employeeRepository.deleteByName(employee.getName());
			if(noOfRecords != 0) {
				result = " Employee with id:"+employee.getName()+" deleted successfully";
			}else {
				result = "Unable to delete Employee with Name:"+employee.getName();
			}
		}
		
		return result;
	}

	@Transactional
	public String deleteAll() {
		String result = null;
		try {
		employeeRepository.deleteAll();
		result = " Employees deleted successfully";
		}
		catch(Exception e) {
			e.printStackTrace();
			result = "Unable to delete Employees";
		}
		return result;
	}

	@Transactional
	public String saveEmployee(Employee employee) {
		Dept dept = new Dept();
		dept.setDeptId(employee.getDeptId());
		employee.setDepartment(dept);
		String result = null;
		try {
		employeeRepository.save(employee);
		result = " Employee with Id: "+employee.getId()+" saved successfully";
		}
		catch(Exception e) {
			e.printStackTrace();
			result = " Unable to save Employee with Id: "+employee.getId();
		}
		return result;
	}

	@Override
	public Employee get(Employee employee) {
		Employee oldEmployee = null;
		if(employee.getId() != null) {
			Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getId());
			oldEmployee = optionalEmployee.isPresent()? optionalEmployee.get(): null;
		}
		
		if(oldEmployee == null && employee.getName() != null) {
			List<Employee> oldEmployeeList = employeeRepository.findByName(employee.getName());
			if(!CollectionUtils.isEmpty(oldEmployeeList)) {
				oldEmployee = oldEmployeeList.get(0);
			}
	}
		
		return oldEmployee;

}
}