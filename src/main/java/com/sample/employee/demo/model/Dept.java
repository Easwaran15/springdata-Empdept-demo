package com.sample.employee.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Dept {
	@Id
	@Column(name = "DEPT_ID")
	private String deptId;
	
	@Column(name="DEPARTMENTNAME")
	private String deptName;

	public Dept() {
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	


}
