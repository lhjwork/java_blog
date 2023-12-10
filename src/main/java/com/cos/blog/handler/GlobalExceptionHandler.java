package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	//IllegalArgumentException 만 받음
	@ExceptionHandler(value=IllegalArgumentException.class)
	public String handleArgumentException(IllegalArgumentException e) {
		return "<h1>"+ e.getMessage() + "</h1>";
	}
	
	//Exception -> 모든 Exception을 받음
	@ExceptionHandler(value=Exception.class)
	public String handleException(IllegalArgumentException e) {
		return "<h1>"+ e.getMessage() + "</h1>";
	}

}
