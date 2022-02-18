package com.bekici.employeeservice.repo;

import com.bekici.employeeservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Employee findByUsername(String username);
}
