package com.fmi.uni.sofia.www.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fmi.uni.sofia.www.errors.ValidationException;
import com.fmi.uni.sofia.www.objects.StatusMessage;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = ValidationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody StatusMessage validationErrorHandler(HttpServletRequest req, ValidationException e) {
		return StatusMessage.errorStatusMessage("Validation exception!");
	}
	
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody StatusMessage globalExceptionHandler(HttpServletRequest req, Exception e) {
		e.printStackTrace();
		String message = "Internal server error!";
		return StatusMessage.errorStatusMessage(message);
	}
}
