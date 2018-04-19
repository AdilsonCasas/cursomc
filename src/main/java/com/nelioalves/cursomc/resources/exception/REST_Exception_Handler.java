package com.nelioalves.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.nelioalves.cursomc.services.exception.Service_Exception_FileException;

@ControllerAdvice
public class REST_Exception_Handler {

	@ExceptionHandler(ObjectNotFoundException.class)
	ResponseEntity<REST_exceptionStandardError> metodoREST_Exception_ObjNaoEncontrado(ObjectNotFoundException e, HttpServletRequest var_request) {
		REST_exceptionStandardError var_err = new REST_exceptionStandardError(HttpStatus.NOT_FOUND.value(),	"Objeto não encontrado!", System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(var_err);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	ResponseEntity<REST_exceptionStandardError> metodoREST_Exception_NaoPodeExcluir(DataIntegrityViolationException e, HttpServletRequest var_request) {
		REST_exceptionStandardError var_err = new REST_exceptionStandardError(HttpStatus.BAD_REQUEST.value(), "Não é possível Excluir porque possui relações externas no BD.", System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(var_err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<REST_exceptionStandardError> metodoREST_Exception_ErroValidacao(MethodArgumentNotValidException e, HttpServletRequest var_request) {
		REST_exceptionValidationError var_err = new REST_exceptionValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de Validação", System.currentTimeMillis());
		for(FieldError x: e.getBindingResult().getFieldErrors()) {
			var_err.REST_exceptionValidationError_addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(var_err);
	}
	
	@ExceptionHandler(AuthorizationServiceException.class)
	ResponseEntity<REST_exceptionStandardError> metodoREST_Exception_UsuarioNaoAutorizado(AuthorizationServiceException e, HttpServletRequest var_request) {
		REST_exceptionStandardError var_err = new REST_exceptionStandardError(HttpStatus.FORBIDDEN.value(),	"Objeto não encontrado!", System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(var_err);
	}

	@ExceptionHandler(Service_Exception_FileException.class)
	ResponseEntity<REST_exceptionStandardError> metodoREST_Exception_FileException(Service_Exception_FileException e, HttpServletRequest var_request) {
		REST_exceptionStandardError var_err = new REST_exceptionStandardError(HttpStatus.BAD_REQUEST.value(),	"Objeto (File Exception) não encontrado!", System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(var_err);
	}

	@ExceptionHandler(AmazonServiceException.class)
	ResponseEntity<REST_exceptionStandardError> metodoREST_Exception_AmazonException(AmazonServiceException e, HttpServletRequest var_request) {
		HttpStatus var_codeStatus = HttpStatus.valueOf(e.getErrorCode());
		REST_exceptionStandardError var_err = new REST_exceptionStandardError(var_codeStatus.value(),	"Objeto(Amazon Service) não encontrado!", System.currentTimeMillis());
		return ResponseEntity.status(var_codeStatus).body(var_err);
	}

	@ExceptionHandler(AmazonClientException.class)
	ResponseEntity<REST_exceptionStandardError> metodoREST_Exception_AmazonClientException(AmazonClientException e, HttpServletRequest var_request) {
		REST_exceptionStandardError var_err = new REST_exceptionStandardError(HttpStatus.BAD_REQUEST.value(),	"Objeto (Amazon Client) não encontrado!", System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(var_err);
	}

	@ExceptionHandler(AmazonS3Exception.class)
	ResponseEntity<REST_exceptionStandardError> metodoREST_Exception_AmazonClientException(AmazonS3Exception e, HttpServletRequest var_request) {
		REST_exceptionStandardError var_err = new REST_exceptionStandardError(HttpStatus.BAD_REQUEST.value(),	"Objeto (S3) não encontrado!", System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(var_err);
	}
}
