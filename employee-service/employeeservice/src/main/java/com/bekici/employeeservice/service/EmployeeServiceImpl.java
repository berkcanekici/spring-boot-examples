package com.bekici.employeeservice.service;

import com.bekici.employeeservice.model.Employee;
import com.bekici.employeeservice.model.Role;
import com.bekici.employeeservice.repo.EmployeeRepo;
import com.bekici.employeeservice.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {
    private final EmployeeRepo employeeRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Employee employee = employeeRepo.findByUsername(username);

        if (employee == null) {
            log.error("Employee not found in the database");
            throw new UsernameNotFoundException("Employee not found in the database");
        }
        else
            log.info("Employee found in the database: {}", username);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        employee.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(employee.getUsername(), employee.getPassword(), authorities);

    }

    @Override
    public Employee saveEmployee(Employee employee)
    {
        log.info("Saving new employee {} to the database.", employee.getName());
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));

        return employeeRepo.save(employee);
    }

    @Override
    public Role saveRole(Role role)
    {
        log.info("Saving new role {} to the database.", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToEmployee(String username, String rolename)
    {
        log.info("Adding role {} to user {}.", rolename, username);
        Employee employee = employeeRepo.findByUsername(username);
        Role role = roleRepo.findByName(rolename);
        employee.getRoles().add(role);
    }

    @Override
    public Employee getUser(String username)
    {
        log.info("Fetching user {}", username);

        return employeeRepo.findByUsername(username);
    }

    @Override
    public List<Employee> getEmployees()
    {
        log.info("Fetching all users");
        return employeeRepo.findAll();
    }

}
