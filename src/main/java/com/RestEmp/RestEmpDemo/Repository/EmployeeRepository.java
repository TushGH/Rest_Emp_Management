package com.RestEmp.RestEmpDemo.Repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.RestEmp.RestEmpDemo.entity.Employee;



public interface EmployeeRepository extends CrudRepository<Employee , String>{
	

}
