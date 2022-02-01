package com.bekici.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() 
	{
		return employeeRepository.findAll();
	}
}
