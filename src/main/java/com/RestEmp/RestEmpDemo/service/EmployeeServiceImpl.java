package com.RestEmp.RestEmpDemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RestEmp.RestEmpDemo.Repository.EmployeeRepository;
import com.RestEmp.RestEmpDemo.entity.Employee;
import com.RestEmp.RestEmpDemo.exception.BadRequestException;
import com.RestEmp.RestEmpDemo.exception.ResourceNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
	EmployeeRepository employeeRepository ;
    
    public List<Employee> findAll(){
    	return (List<Employee>)employeeRepository.findAll();
    }
    
    public Employee findOne(String id) {
    	
    	Optional<Employee> existing = employeeRepository.findById(id); 
    	if(!existing.isPresent()) {
    		throw new ResourceNotFoundException("Employee with Id " + id + "Not present");
    	}
    	return existing.get() ;
    }
    
    public Employee create(Employee emp) {
    	
    	if(emp == null) {
    		throw new BadRequestException("Check Body of request");
    	}
    	return employeeRepository.save(emp);
    }
    
    
    public Employee update(Employee emp) {
    	
    	if(emp == null) {
    		throw new BadRequestException("Check Body of request");
    	}
    	return employeeRepository.save(emp);
    }
    
    public void delete(String id ) {
    	Optional<Employee> existing = employeeRepository.findById(id); 
    	if(!existing.isPresent()) {
    		throw new ResourceNotFoundException("Employee with Id " + id + "Not present");
    	}
    	employeeRepository.delete(existing.get()); ;
    }
    
    
}
