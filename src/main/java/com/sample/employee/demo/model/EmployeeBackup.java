package com.sample.employee.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EmployeeBackup {
	@Id
	private String id;
	private String name;
	private String designation;
	
	@Column(name="DEPT_ID")
	private String deptId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Override
	public String toString() {
		return "EmployeeBackup [id=" + id + ", name=" + name + ", designation=" + designation + ", deptId=" + deptId
				+ "]";
	}
	
}
