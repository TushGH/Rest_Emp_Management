package com.RestEmp.RestEmpDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.RestEmp.RestEmpDemo.entity.Employee;
import com.RestEmp.RestEmpDemo.service.EmployeeService;

@RestController
@RequestMapping(value = "/employees")
public class restController {
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Employee> findAll(){
		return employeeService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET , value = "/{id}",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Employee findOne(@PathVariable("id") String empid){
		
		return employeeService.findOne(empid);
		
	}
	
	@RequestMapping(method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE ,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Employee create(@RequestBody Employee emp){
		System.out.println(emp);
		return employeeService.create(emp);
		
	}
	
	
	@RequestMapping(method = RequestMethod.PUT , value = "/{id}",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE ,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Employee update(@RequestBody Employee emp , @PathVariable String id){
		
		return employeeService.update(emp);
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE , value = "/{id}",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void delete(@PathVariable("id") String empid){
		
		 employeeService.delete(empid);
		
	}
	
}
