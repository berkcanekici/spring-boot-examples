package com.bekici.springboot.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity()
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		auth.inMemoryAuthentication()
				.withUser("berkcan")
				.password("2344")
				.roles("USER")
				.and()
				.withUser("yusuf")
				.password("7644")
				.roles("ADMIN");
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http.authorizeRequests()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
				//.antMatchers("/").permitAll()
				.and().formLogin();
	}

	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder getPassword()
	{
		return NoOpPasswordEncoder.getInstance();
	}
}
