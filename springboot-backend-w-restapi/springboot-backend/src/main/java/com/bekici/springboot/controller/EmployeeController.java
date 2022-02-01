package com.bekici.springboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
		this.employeeService = employeeService;
	}
	
	// Build create employee with RestApi
	@PostMapping("/saveEmployee")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee)
	{
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}
	
	// Build get all employees with RestApi
	@GetMapping("/getAllEmployees")
	public List<Employee> getAllEmployees()
	{
		return employeeService.getAllEmployees();
	}
}
