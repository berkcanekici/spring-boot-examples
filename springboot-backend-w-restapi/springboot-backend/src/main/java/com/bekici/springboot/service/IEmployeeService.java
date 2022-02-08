package com.bekici.springboot.service;

import java.util.List;

import com.bekici.springboot.model.Employee;

public interface IEmployeeService {
	public Employee saveEmployee(Employee employee);
	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(long id);
	public List<Employee> getEmployeeByFirstName(String firstName);
	public List<Employee> getEmployeeByLastName(String lastName);
	public Employee getEmployeeByEmail(String email);
	public Employee updateEmployeeById(Employee employee, long id);
	public Employee updateEmployeeByEmail(Employee employee, String email);
	public void deleteEmployeeById(long id);
	public void deleteEmployeeByFirstName(String firstName);
	public void deleteEmployeeByLastName(String lastName);
	public void deleteEmployeeByMail(String email);
}
