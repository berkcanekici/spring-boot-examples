package com.bekici.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.bekici.springboot.model.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
	public List<Employee> findByFirstName(String firstName);
	public List<Employee> findByLastName(String lastName);
	public Optional<Employee> findByEmail(String email);
	public boolean existsByEmail(String email);
	
	@Transactional
	public void deleteByFirstName(String firstName);
	@Transactional
	public void deleteByLastName(String lastName);
	@Transactional
	public void deleteByEmail(String email);
}
