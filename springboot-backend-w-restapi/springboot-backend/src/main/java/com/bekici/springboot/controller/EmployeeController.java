package com.bekici.springboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bekici.springboot.model.Employee;
import com.bekici.springboot.service.IEmployeeService;

@RestController
//@RequestMapping("/api")
public class EmployeeController {
	private IEmployeeService employeeService;
	
	public EmployeeController(IEmployeeService employeeService) 
	{ 	
		this.employeeService = employeeService;
	}
	
	// ---------------------------------------------------- POST OPERATIONS ----------------------------------------------------
	
	@PostMapping("/admin/save-employee")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee)
	{	
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}
	
	// ---------------------------------------------------- GET OPERATIONS ---------------------------------------------------- 
	@GetMapping("/")
	public String home()
	{
		return "<h1>Welcome!..</h1>";
	}
	
	@GetMapping("/admin")
	public String admin()
	{
		return "<h1>Welcome ADMIN!..</h1>";
	}
	
	@GetMapping("/user")
	public String user()
	{
		return "<h1>Welcome USER!..</h1>";
	}
	
	
	@GetMapping("/admin/get/all-employees")
	public List<Employee> getAllEmployees()
	{
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/user/get/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id)
	{
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(id), HttpStatus.OK);
	}
	
	@GetMapping("/admin/get/first-name/{firstName}")
	public ResponseEntity<List<Employee>> getEmployeeByFirstName(@PathVariable("firstName") String firstName)
	{
		return new ResponseEntity<List<Employee>>(employeeService.getEmployeeByFirstName(firstName), HttpStatus.OK);
	}
	
	@GetMapping("/admin/get/last-name/{lastName}")
	public ResponseEntity<List<Employee>> getEmployeeByLastName(@PathVariable("lastName") String lastName)
	{
		return new ResponseEntity<List<Employee>>(employeeService.getEmployeeByLastName(lastName), HttpStatus.OK);
	}
	
	@GetMapping("/user/get/email/{email}")
	public ResponseEntity<Employee> getEmployeeByEmail(@PathVariable("email") String email)
	{
		return new ResponseEntity<Employee>(employeeService.getEmployeeByEmail(email), HttpStatus.OK);
	}
	
	// ---------------------------------------------------- PUT OPERATIONS ---------------------------------------------------- 
	
	@PutMapping("/admin/update/id/{id}")
	public ResponseEntity<Employee> updateEmployeeById(@PathVariable("id") long id,
												   @RequestBody Employee employee)
	{
		return new ResponseEntity<Employee>(employeeService.updateEmployeeById(employee, id), HttpStatus.OK);
	}
	
	@PutMapping("/admin/update/email/{email}")
	public ResponseEntity<Employee> updateEmployeeByEmail(@PathVariable("email") String email,
												   @RequestBody Employee employee)
	{
		return new ResponseEntity<Employee>(employeeService.updateEmployeeByEmail(employee, email), HttpStatus.OK);
	}
	
	// ---------------------------------------------------- DELETE OPERATIONS ---------------------------------------------------- 
	
	@DeleteMapping("/admin/delete/id/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") long id)
	{
		employeeService.deleteEmployeeById(id);
		
		return new ResponseEntity<String>("According to the id, Employee successfully deleted.", HttpStatus.OK);
	}
	
	@DeleteMapping("/admin/delete/first-name/{firstName}")
	public ResponseEntity<String> deleteEmployeeByFirstName(@PathVariable("firstName") String firstName)
	{
		employeeService.deleteEmployeeByFirstName(firstName);
		
		return new ResponseEntity<String>("According to the first name, Employee successfully deleted.", HttpStatus.OK);
	}
	
	@DeleteMapping("/admin/delete/last-name/{lastName}")
	public ResponseEntity<String> deleteEmployeeByLastName(@PathVariable("lastName") String lastName)
	{
		employeeService.deleteEmployeeByLastName(lastName);
		
		return new ResponseEntity<String>("According to the last name, Employee successfully deleted.", HttpStatus.OK);
	}
	
	@DeleteMapping("/admin/delete/email/{email}")
	public ResponseEntity<String> deleteEmployeeByEmail(@PathVariable("email") String email)
	{
		employeeService.deleteEmployeeByMail(email);
		
		return new ResponseEntity<String>("According to the mail, Employee successfully deleted.", HttpStatus.OK);
	}
}
