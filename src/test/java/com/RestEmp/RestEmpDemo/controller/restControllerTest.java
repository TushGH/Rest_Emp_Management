package com.RestEmp.RestEmpDemo.controller;



import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.RestEmp.RestEmpDemo.Repository.EmployeeRepository;
import com.RestEmp.RestEmpDemo.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK
		)

@AutoConfigureMockMvc
@ActiveProfiles("IntegrationTest")
public class restControllerTest {
	
	@Autowired
	private MockMvc mvc ;
	
	@Autowired
	private EmployeeRepository repository ;
	
	
	@Before
	public void setup() {
		Employee emp1 = new Employee();
		emp1.setId("aaa1");
		emp1.setEmail("aaa");
		emp1.setFirstName("aaa");
		emp1.setLastName("aa");
		repository.save(emp1);
	
		
		
	}
	
	@After
	public void cleanup() {
		repository.deleteAll();
	}

	@Test
	public void testFindAll() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/employees"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].email", Matchers.is("aaa")));
	}

	@Test
	public void testFindOne() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/employees/aaa1"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("aaa")));
	}
	
	@Test
	public void testFindOneException() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/employees/sss"))
		.andExpect(MockMvcResultMatchers.status().isNotFound());
		
	}

	@Test
	public void testCreate() throws Exception {
		ObjectMapper ob = new ObjectMapper();
		Employee emp2 = new Employee();
		emp2.setEmail("aabba");
		emp2.setFirstName("aaa");
		emp2.setLastName("aa");
		
		mvc.perform(MockMvcRequestBuilders.post("/employees")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(ob.writeValueAsBytes(emp2)))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("aabba")));
	}

}
