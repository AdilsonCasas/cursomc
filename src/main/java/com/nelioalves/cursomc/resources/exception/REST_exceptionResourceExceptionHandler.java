package com.nelioalves.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nelioalves.cursomc.resources.REST_exceptionStandardError;
import com.nelioalves.cursomc.resources.REST_exceptionValidationError;

@ControllerAdvice
public class REST_exceptionResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	ResponseEntity<REST_exceptionStandardError> UmNomeQualquerDeFuncao_1(ObjectNotFoundException e, HttpServletRequest request) {
		REST_exceptionStandardError err = new REST_exceptionStandardError(HttpStatus.NOT_FOUND.value(),	"Objeto não encontrado! Id: "+e.getClass(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	//ResponseEntity<REST_exceptionStandardError> UmNomeQualquerDeFuncao_2(service_exceptionGenericRuntimeException e, HttpServletRequest request) {
	ResponseEntity<REST_exceptionStandardError> UmNomeQualquerDeFuncao_2(DataIntegrityViolationException e, HttpServletRequest request) {
		REST_exceptionStandardError err = new REST_exceptionStandardError(HttpStatus.BAD_REQUEST.value(), "Não é possível Excluir uma Categoria que possui produtos associados, ClassName="+e.getClass().getName(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<REST_exceptionStandardError> UmNomeQualquerDeFuncao_3(MethodArgumentNotValidException e, HttpServletRequest request) {
		REST_exceptionValidationError err = new REST_exceptionValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de Validação", System.currentTimeMillis());
		for(FieldError x: e.getBindingResult().getFieldErrors()) {
			err.REST_exceptionValidationError_addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
}
