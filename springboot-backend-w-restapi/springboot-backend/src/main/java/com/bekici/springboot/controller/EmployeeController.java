package com.bekici.springboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bekici.springboot.model.Employee;
import com.bekici.springboot.service.IEmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	private IEmployeeService employeeService;

	public EmployeeController(IEmployeeService employeeService) 
	{
		super();
		this.employeeService = employeeService;
	}
	
	// Build create employee with RestApi
	@PostMapping()
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee)
	{
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}
	
	
	
	
	// test line
}
