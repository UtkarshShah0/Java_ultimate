package com.tcs.boot.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{

	
	@ExceptionHandler(value = {IllegalArgumentException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Map<String, MyErrorResponse>> handleHandler(IllegalArgumentException ex){
		
		MyErrorResponse response = new MyErrorResponse();
		response.setErrorCode("400");
		response.setMessage(ex.getMessage());
		response.setTime(new java.util.Date(2025, 11, 28, 15, 05));
		
		Map<String, MyErrorResponse> errorMap = new HashMap<>();
		errorMap.put("error", response);
		return ResponseEntity.status(400).body(errorMap);
	}
	
	// Genric
	@ExceptionHandler(value = {Exception.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Map<String, MyErrorResponse>> handleHandler(Exception ex){
		
		MyErrorResponse response = new MyErrorResponse();
		response.setErrorCode("404");
		response.setMessage(ex.getMessage());
		response.setTime(new java.util.Date(2025, 11, 28, 15, 05));
		
		Map<String, MyErrorResponse> errorMap = new HashMap<>();
		errorMap.put("error", response);
		return ResponseEntity.status(404).body(errorMap);
	}
	
}
