package com.bekici.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bekici.springboot.model.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

}
