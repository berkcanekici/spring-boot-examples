package com.bekici.employeeservice.service;

import com.bekici.employeeservice.model.Employee;
import com.bekici.employeeservice.model.Role;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    Role saveRole(Role role);
    void addRoleToEmployee(String username, String rolename);
    Employee getUser(String username);
    List<Employee> getEmployees();
}
