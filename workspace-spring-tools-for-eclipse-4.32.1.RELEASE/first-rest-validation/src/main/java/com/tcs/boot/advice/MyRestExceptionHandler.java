package com.tcs.boot.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tcs.boot.handler.ErrorResponse;

@RestControllerAdvice
public class MyRestExceptionHandler {

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)

	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		System.out.println("From MANVE");
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return ResponseEntity.status(400).body(errorMap);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
		System.out.println("Custom 405 handler called");
		ErrorResponse errorResponse = new ErrorResponse("Method Not Allowed", ex.getMessage());
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorResponse);
	}

//	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//	public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		System.out.println("from mna");
//
//		ErrorResponse errorResponse = new ErrorResponse("Method Not Allowed", ex.getMessage());
//
//		return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
//	}

//	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
//	        HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//		  System.out.println("from metns");
//		ErrorResponse errorResponse = new ErrorResponse("Unsupported Yo Man Media Type", ex.getMessage());
//	    
//	    return new ResponseEntity<>(errorResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
//	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
		System.out.println("Custom handler called");
		ErrorResponse errorResponse = new ErrorResponse("Unsupported Yo Man Media Type", ex.getMessage());
		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(errorResponse);
	}

	@ExceptionHandler(value = { NullPointerException.class })
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	protected ResponseEntity<Object> errorHandler(NullPointerException e, WebRequest req) {
		System.out.println("from npe");
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(e.getMessage());
//		 errorResponse.setErrorCode("406");
//		 errorResponse.setTime(new java.util.Date());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
		// return handleExceptionInternal(e,error, new HttpHeaders(),
		// HttpStatus.NOT_ACCEPTABLE,req); // 5 arguments

	}

	@ExceptionHandler(value = { Exception.class })

	@ResponseStatus(HttpStatus.NOT_FOUND)
	protected ResponseEntity<Object> errorHandler(Exception e, WebRequest req) {
		System.out.println("from Exc");
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(e.getMessage());
//		  errorResponse.setErrorCode("404"); 
//		  errorResponse.setTime(new java.util.Date()); 
		return new ResponseEntity<>(errorResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
		// return //handleExceptionInternal(e,error,new HttpHeaders(),
		// HttpStatus.NOT_FOUND,req);

	}

	@ExceptionHandler(value = { InvalidValueException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> errorHandler(InvalidValueException e, WebRequest req) {
		System.out.println("from ivEx");
		ErrorResponse errorResponse = new ErrorResponse();
//		  errorResponse.setMessage(e.getMessage()); errorResponse.setErrorCode("400");
//		  errorResponse.setTime(new java.util.Date()); 
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		// handleExceptionInternal(e,errorResponse,new
		// HttpHeaders(),HttpStatus.BAD_REQUEST,req);

	}

}