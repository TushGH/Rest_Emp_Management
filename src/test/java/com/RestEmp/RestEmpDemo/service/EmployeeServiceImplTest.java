package com.RestEmp.RestEmpDemo.service;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.RestEmp.RestEmpDemo.Repository.EmployeeRepository;
import com.RestEmp.RestEmpDemo.entity.Employee;
import com.RestEmp.RestEmpDemo.exception.ResourceNotFoundException;





@RunWith(SpringRunner.class)
public class EmployeeServiceImplTest {
    private List<Employee> emplist;
	@TestConfiguration
	static class testannotation{
		@Bean
		public EmployeeService getEmployeeService() {
			return new EmployeeServiceImpl();
		}
	}
	@Autowired
	private EmployeeService employeeService ;
	
	@MockBean
	private  EmployeeRepository employeeRepository ;
	
	@Before
	public void setup() {
		Employee emp = new Employee();
		emp.setEmail("aaa");
		emp.setFirstName("aaa");
		emp.setLastName("aa");
		emplist = Collections.singletonList(emp);
		Mockito.when(employeeRepository.findAll()).thenReturn(emplist);
		
		Mockito.when(employeeRepository.findById(emp.getId())).thenReturn(Optional.of(emp));
	
	}
	
	
	public void testFindAll() {
		List<Employee> employees = (List<Employee>) employeeRepository.findAll();
	    Assert.assertEquals(emplist, employees);
		
	}

	@Test
	public void testFindOne() {
		Employee result = employeeService.findOne(emplist.get(0).getId());
		Assert.assertEquals(emplist.get(0), result);
		
	}
	
	@Test(expected =  ResourceNotFoundException.class)
	public void testFindOneNotFound() {
		Employee result = employeeService.findOne("aaaaa");
		Assert.assertEquals(emplist.get(0), result);
		
	}


	@After
	public void cleanup() {
		
	}

}
