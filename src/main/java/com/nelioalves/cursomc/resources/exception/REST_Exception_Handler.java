package com.nelioalves.cursomc.resources.exception;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.nelioalves.cursomc.enumErroPadrao;
import com.nelioalves.cursomc.resources.utils.REST_Utils_URL;

@ControllerAdvice
public class REST_Exception_Handler {
	
	// Obs geral: o erro 422 (coloque a pesquisa "http 422" no google e veja que é um erro tipo "unprocessable entity" e já está sendo muito usado
	// para erros de validaçãode formulários. Este erro faz sentido como erro de validação e pode ser usado para validação de formulário


	private REST_exceptionStandardError metodoREST_Exception_PreparaMsgErroPadrao(String var_class, String var_msgErro, HttpStatus var_httpStatus, HttpServletRequest var_request) {

        // chamada da função --> 'throw new Exception("ERRO_PADRAO#0001@"+"...");', sendo que os "..." é uma personalização da msg que deve ser trabalhada,
		//                        ex: quando dá erro de autorização em um usuário a chamada será --> '"ERRO_PADRAO#0016@"+var_email'
		//                            nos outros casos que não necessitam de "msg complementar" será igual a "NOT_DISPLAY"

		REST_exceptionStandardError var_err;
		if (var_msgErro.startsWith("ERRO_PADRAO#")) {
			String var_NumErro = var_msgErro.substring(var_msgErro.indexOf('#')+1,var_msgErro.indexOf('@'));
			if (enumErroPadrao.toEnum(Integer.parseInt(var_NumErro)).getMsgComplementar().trim().equals("NOT_DISPLAY")) {
				var_err = new REST_exceptionStandardError(REST_Utils_URL.metodoREST_utils_formataData_e_Hora_fromTimeStamp(System.currentTimeMillis()), var_httpStatus.value(),	var_httpStatus.name(), enumErroPadrao.toEnum(Integer.parseInt(var_NumErro)).getMsgPadraoParaErro(), var_request.getRequestURI());
			}
			else {
				String var_MsgComplementar = var_msgErro.substring(var_msgErro.indexOf('@')+1);
				var_err = new REST_exceptionStandardError(REST_Utils_URL.metodoREST_utils_formataData_e_Hora_fromTimeStamp(System.currentTimeMillis()), var_httpStatus.value(),	var_httpStatus.name(), enumErroPadrao.toEnum(Integer.parseInt(var_NumErro)).getMsgPadraoParaErro()+" ("+var_MsgComplementar.trim()+")", var_request.getRequestURI());
			}
		}
		else {
			var_err = new REST_exceptionStandardError(REST_Utils_URL.metodoREST_utils_formataData_e_Hora_fromTimeStamp(System.currentTimeMillis()), var_httpStatus.value(),	var_httpStatus.name(), "("+var_class+"): "+var_msgErro, var_request.getRequestURI());
		}
		return var_err;
	}

	@ExceptionHandler(Exception.class) // usada para erro padrão feito manualmente por Adilson
	ResponseEntity<REST_exceptionStandardError> metodoREST_Exception_ErroPadrao(Exception e, HttpServletRequest var_request) {
		REST_exceptionStandardError var_err = metodoREST_Exception_PreparaMsgErroPadrao("Exception.class", e.getMessage(), HttpStatus.NOT_FOUND, var_request);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(var_err);
	}

	@ExceptionHandler(RuntimeException.class) // usada para erro padrão feito manualmente por Adilson
	ResponseEntity<REST_exceptionStandardError> metodoREST_Exception_RuntimeException(RuntimeException e, HttpServletRequest var_request) {
		REST_exceptionStandardError var_err = metodoREST_Exception_PreparaMsgErroPadrao("RuntimeException.class", e.getMessage(), HttpStatus.BAD_REQUEST, var_request);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(var_err);
	}

	@ExceptionHandler(IllegalArgumentException.class) // usada para erro padrão feito manualmente por Adilson
	ResponseEntity<REST_exceptionStandardError> metodoREST_Exception_IllegalArgumentException(IllegalArgumentException e, HttpServletRequest var_request) {
		REST_exceptionStandardError var_err = metodoREST_Exception_PreparaMsgErroPadrao("IllegalArgumentException.class", e.getMessage(), HttpStatus.BAD_REQUEST, var_request);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(var_err);
	}

	@ExceptionHandler(ObjectNotFoundException.class)
	ResponseEntity<REST_exceptionStandardError> metodoREST_Exception_ObjectNotFoundException(ObjectNotFoundException e, HttpServletRequest var_request) {
		REST_exceptionStandardError var_err = metodoREST_Exception_PreparaMsgErroPadrao("ObjectNotFoundException.class", e.getMessage(), HttpStatus.NOT_FOUND, var_request);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(var_err);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	ResponseEntity<REST_exceptionStandardError> metodoREST_Exception_DataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest var_request) {
		REST_exceptionStandardError var_err = metodoREST_Exception_PreparaMsgErroPadrao("DataIntegrityViolationException.class", e.getMessage(), HttpStatus.BAD_REQUEST, var_request);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(var_err);
	}

	@ExceptionHandler(MessagingException.class)
	ResponseEntity<REST_exceptionStandardError> metodoREST_Exception_MessagingException(MessagingException e, HttpServletRequest var_request) {
		REST_exceptionStandardError var_err = metodoREST_Exception_PreparaMsgErroPadrao("MessagingException.class", e.getMessage(), HttpStatus.BAD_REQUEST, var_request);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(var_err);
	}

	@ExceptionHandler(UnsupportedEncodingException.class)
	ResponseEntity<REST_exceptionStandardError> metodoREST_Exception_UnsupportedEncodingException(UnsupportedEncodingException e, HttpServletRequest var_request) {
		REST_exceptionStandardError var_err = metodoREST_Exception_PreparaMsgErroPadrao("UnsupportedEncodingException.class", e.getMessage(), HttpStatus.BAD_REQUEST, var_request);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(var_err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<REST_exceptionStandardError> metodoREST_Exception_MethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest var_request) {
		REST_exceptionValidationError var_err = new REST_exceptionValidationError(REST_Utils_URL.metodoREST_utils_formataData_e_Hora_fromTimeStamp(System.currentTimeMillis()), HttpStatus.UNPROCESSABLE_ENTITY.value(),	"Erro de Validação!", e.getMessage(), var_request.getRequestURI());
		for(FieldError x: e.getBindingResult().getFieldErrors()) {
			var_err.REST_exceptionValidationError_addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(var_err);
	}
	
	@ExceptionHandler(ParseException.class)
	ResponseEntity<REST_exceptionStandardError> metodoREST_Exception_ParseException(ParseException e, HttpServletRequest var_request) {
		REST_exceptionStandardError var_err = metodoREST_Exception_PreparaMsgErroPadrao("ParseException.class", e.getMessage(), HttpStatus.BAD_REQUEST, var_request);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(var_err);
	}
	
	@ExceptionHandler(AuthorizationServiceException.class)
	ResponseEntity<REST_exceptionStandardError> metodoREST_Exception_AuthorizationServiceException(AuthorizationServiceException e, HttpServletRequest var_request) {
		REST_exceptionStandardError var_err = metodoREST_Exception_PreparaMsgErroPadrao("AuthorizationServiceException.class", e.getMessage(), HttpStatus.UNAUTHORIZED, var_request);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(var_err);
	}

	
	@ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
	ResponseEntity<REST_exceptionStandardError> metodoREST_Exception_AuthenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException e, HttpServletRequest var_request) {
		REST_exceptionStandardError var_err = metodoREST_Exception_PreparaMsgErroPadrao("AuthenticationCredentialsNotFoundException.class", e.getMessage(), HttpStatus.UNAUTHORIZED, var_request);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(var_err);
	}


	@ExceptionHandler(IOException.class)
	ResponseEntity<REST_exceptionStandardError> metodoREST_Exception_IOException(IOException e, HttpServletRequest var_request) {
		REST_exceptionStandardError var_err = metodoREST_Exception_PreparaMsgErroPadrao("IOException.class", e.getMessage(), HttpStatus.BAD_REQUEST, var_request);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(var_err);
	}

	@ExceptionHandler(AmazonServiceException.class)
	ResponseEntity<REST_exceptionStandardError> metodoREST_Exception_AmazonServiceException(AmazonServiceException e, HttpServletRequest var_request) {
		HttpStatus var_codeStatus = HttpStatus.valueOf(e.getErrorCode());
		//REST_exceptionStandardError var_err = new REST_exceptionStandardError(REST_Utils_URL.metodoREST_utils_formataData_e_Hora_fromTimeStamp(System.currentTimeMillis()), var_codeStatus.value(), "Objeto(Amazon Service) não encontrado!", e.getMessage(), var_request.getRequestURI());
		REST_exceptionStandardError var_err = metodoREST_Exception_PreparaMsgErroPadrao("AmazonServiceException.class", e.getMessage(), var_codeStatus, var_request);
		return ResponseEntity.status(var_codeStatus).body(var_err);
	}

	@ExceptionHandler(AmazonClientException.class)
	ResponseEntity<REST_exceptionStandardError> metodoREST_Exception_AmazonClientException(AmazonClientException e, HttpServletRequest var_request) {
		//REST_exceptionStandardError var_err = new REST_exceptionStandardError(REST_Utils_URL.metodoREST_utils_formataData_e_Hora_fromTimeStamp(System.currentTimeMillis()), HttpStatus.BAD_REQUEST.value(),	"Objeto (Amazon Client) não encontrado!", e.getMessage(), var_request.getRequestURI());
		REST_exceptionStandardError var_err = metodoREST_Exception_PreparaMsgErroPadrao("AmazonClientException.class", e.getMessage(), HttpStatus.BAD_REQUEST, var_request);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(var_err);
	}

	@ExceptionHandler(AmazonS3Exception.class)
	ResponseEntity<REST_exceptionStandardError> metodoREST_Exception_AmazonS3Exception(AmazonS3Exception e, HttpServletRequest var_request) {
		//REST_exceptionStandardError var_err = new REST_exceptionStandardError(REST_Utils_URL.metodoREST_utils_formataData_e_Hora_fromTimeStamp(System.currentTimeMillis()), HttpStatus.BAD_REQUEST.value(),	"Objeto (S3) não encontrado!", e.getMessage(), var_request.getRequestURI());
		REST_exceptionStandardError var_err = metodoREST_Exception_PreparaMsgErroPadrao("AmazonS3Exception.class", e.getMessage(), HttpStatus.BAD_REQUEST, var_request);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(var_err);
	}
}
