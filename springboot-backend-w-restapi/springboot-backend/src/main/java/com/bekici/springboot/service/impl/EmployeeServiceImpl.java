package com.bekici.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bekici.springboot.exception.BadRequestException;
import com.bekici.springboot.exception.ResourceNotFoundException;
import com.bekici.springboot.model.Employee;
import com.bekici.springboot.repository.IEmployeeRepository;
import com.bekici.springboot.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	private IEmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl(IEmployeeRepository employeeRepository) 
	{
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) 
	{
		if (employeeRepository.existsById(employee.getId()))
			throw new BadRequestException(employee.getId());
		else if (employeeRepository.existsByEmail(employee.getEmail()))
			throw new BadRequestException(employee.getEmail());
		
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() 
	{
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) 
	{
		return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
	}
	
	@Override
	public List<Employee> getEmployeeByFirstName(String firstName) 
	{
		List<Employee> employeeList = employeeRepository.findByFirstName(firstName);
		
		if (employeeList.size() == 0)
			throw new ResourceNotFoundException("Employee", "FirstName", firstName);
		
		return employeeList;
	}

	@Override
	public List<Employee> getEmployeeByLastName(String lastName) 
	{
		List<Employee> employeeList = employeeRepository.findByLastName(lastName);
		
		if (employeeList.size() == 0)
			throw new ResourceNotFoundException("Employee", "LastName", lastName);
		
		return employeeList;
	}

	@Override
	public Employee getEmployeeByEmail(String email) 
	{
		return employeeRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Employee", "Email", email));
	}

	@Override
	public Employee updateEmployeeById(Employee employee, long id) 
	{
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
											() -> new ResourceNotFoundException("Employee", "Id", id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		
		employeeRepository.save(existingEmployee);
		
		return existingEmployee;
	}
	
	@Override
	public Employee updateEmployeeByEmail(Employee employee, String email) 
	{
		Employee existingEmployee = employeeRepository.findByEmail(email).orElseThrow(
											() -> new ResourceNotFoundException("Employee", "Email", email));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		
		employeeRepository.save(existingEmployee);
		
		return existingEmployee;
	}

	@Override
	public void deleteEmployeeById(long id) 
	{
		employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee", "Id", id));
		
		employeeRepository.deleteById(id);
	}
	
	@Override
	public void deleteEmployeeByFirstName(String firstName) 
	{
		if (employeeRepository.findByFirstName(firstName).isEmpty())
			throw new ResourceNotFoundException("Employee", "First Name", firstName);
		
		employeeRepository.deleteByFirstName(firstName); 	
	}
	
	@Override
	public void deleteEmployeeByLastName(String lastName) 
	{
		if (employeeRepository.findByLastName(lastName).isEmpty())
			throw new ResourceNotFoundException("Employee", "Last Name", lastName);
		
		employeeRepository.deleteByLastName(lastName); 	
	}
	
	@Override
	public void deleteEmployeeByMail(String email) 
	{
		employeeRepository.findByEmail(email).orElseThrow(
				() -> new ResourceNotFoundException("Employee", "Mail", email));
		
		employeeRepository.deleteByEmail(email); 	
	}
}
