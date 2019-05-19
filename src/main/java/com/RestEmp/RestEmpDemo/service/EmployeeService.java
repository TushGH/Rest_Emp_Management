package com.RestEmp.RestEmpDemo.service;

import java.util.List;

import com.RestEmp.RestEmpDemo.entity.Employee;

public interface EmployeeService{
	public List<Employee> findAll();
	public Employee findOne(String id);
	public void delete(String id );
	public Employee create(Employee emp);
	public Employee update(Employee emp);
}
