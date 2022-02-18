package com.bekici.employeeservice;

import com.bekici.employeeservice.model.Employee;
import com.bekici.employeeservice.model.Role;
import com.bekici.employeeservice.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class EmployeeserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeserviceApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(EmployeeService employeeService)
	{
		return args -> {
			employeeService.saveRole(new Role(null, "ROLE_USER"));
			employeeService.saveRole(new Role(null, "ROLE_MANAGER"));
			employeeService.saveRole(new Role(null, "ROLE_ADMIN"));
			employeeService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			employeeService.saveEmployee(new Employee(null, "Berkcan Ekici", "berk", "1234", new ArrayList<>()));
			employeeService.saveEmployee(new Employee(null, "Huseyin Ayan", "huso", "5678", new ArrayList<>()));
			employeeService.saveEmployee(new Employee(null, "Yusuf Uslu", "yuso", "4321", new ArrayList<>()));
			employeeService.saveEmployee(new Employee(null, "Firat Kaymaz", "firo", "8765", new ArrayList<>()));

			employeeService.addRoleToEmployee("berk", "ROLE_USER");
			employeeService.addRoleToEmployee("berk", "ROLE_MANAGER");
			employeeService.addRoleToEmployee("huso", "ROLE_MANAGER");
			employeeService.addRoleToEmployee("yuso", "ROLE_MANAGER");
			employeeService.addRoleToEmployee("firo", "ROLE_SUPER_ADMIN");
			employeeService.addRoleToEmployee("firo", "ROLE_ADMIN");
			employeeService.addRoleToEmployee("firo", "ROLE_USER");
		};
	}
}

