package com.renanmateus.diazero.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.renanmateus.diazero.exception.model.Error;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler
	public ResponseEntity<Object> handleIncidentAlredyCloseException(IncidentAlredyCloseException ex,
			WebRequest request) {
		return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	@ExceptionHandler
	public ResponseEntity<Object> handleIncidentNotFoundException(IncidentNotFoundException ex, WebRequest request) {
		return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}


	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (body == null) {
			body = new Error(ex.getMessage(), status.value(), LocalDateTime.now());
			return super.handleExceptionInternal(ex, body, headers, status, request);
		} 
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

}
