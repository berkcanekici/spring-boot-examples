
package com.bekici.springboot.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity()
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	//@Autowired
	//DataSource dataSource;
	
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception 
	{
		auth.inMemoryAuthentication()
				.withUser("berkcan")
				.password("2344")
				.roles("USER")
				.and()
				.withUser("yusuf")
				.password("7644")
				.roles("ADMIN", "USER");
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{	
		http.authorizeRequests()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/user/**").hasRole("USER")
			.antMatchers("/").permitAll()
			.anyRequest().authenticated()
			.and()
			.httpBasic()
	        .and()
	        .exceptionHandling().accessDeniedPage("/403")
	        .and()
			.formLogin();
	}

	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder getPassword()
	{
		return NoOpPasswordEncoder.getInstance();
	}
}
