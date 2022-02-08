package com.bekici.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.CONFLICT)
public class BadRequestException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private long existId;
	private String existEmail;
	
	public BadRequestException(long existId) 
	{
		super(String.format("This id [%d] already exist!..%n", existId));
		this.existId = existId;
	}
	
	public BadRequestException(String existEmail)
	{
		super(String.format("This email [%s] already exist!..%n", existEmail));
		this.existEmail = existEmail;
	}

	public long getExistId() 
	{
		return existId;
	}

	public String getExistEmail() 
	{
		return existEmail;
	}
}
