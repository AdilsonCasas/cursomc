package com.nelioalves.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nelioalves.cursomc.services.exceptions.service_exceptionDataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.service_exceptionObjectNotFoundException;

@ControllerAdvice
public class REST_exceptionResourceExceptionHandler {

	@ExceptionHandler(service_exceptionObjectNotFoundException.class)
	ResponseEntity<REST_exceptionStandardError> UmNomeQualquerDeFuncao_1(service_exceptionObjectNotFoundException e, HttpServletRequest request) {
		REST_exceptionStandardError err = new REST_exceptionStandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(service_exceptionDataIntegrityException.class)
	ResponseEntity<REST_exceptionStandardError> UmNomeQualquerDeFuncao_2(service_exceptionDataIntegrityException e, HttpServletRequest request) {
		REST_exceptionStandardError err = new REST_exceptionStandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
}
