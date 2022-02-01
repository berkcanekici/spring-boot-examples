package com.bekici.springboot.service;

import java.util.List;

import com.bekici.springboot.model.Employee;

public interface IEmployeeService {
	public Employee saveEmployee(Employee employee);
	public List<Employee> getAllEmployees();
}
