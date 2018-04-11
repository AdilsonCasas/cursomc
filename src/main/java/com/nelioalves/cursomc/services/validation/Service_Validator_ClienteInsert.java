package com.nelioalves.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.nelioalves.cursomc.domain.ClienteDomain;
import com.nelioalves.cursomc.domain.dto.DTO_ClienteDomain_Completo;
import com.nelioalves.cursomc.domain.enums.enumTipoCliente;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.resources.REST_exceptionFieldMessage;
import com.nelioalves.cursomc.services.validation.utils.Service_Validation_Utils_BR;

public class Service_Validator_ClienteInsert implements ConstraintValidator<Service_Annotation_ClienteInsert, DTO_ClienteDomain_Completo> {

	@Autowired
	private ClienteRepository var_repoCliente;

	@Override
	public void initialize(Service_Annotation_ClienteInsert ann) {
	}

	@Override
	public boolean isValid(DTO_ClienteDomain_Completo var_objDTO, ConstraintValidatorContext var_context) {
		List<REST_exceptionFieldMessage> var_list = new ArrayList<>();
		if (var_objDTO.getTipoCliente().equals(enumTipoCliente.PESSOAFISICA.getCod()) && !Service_Validation_Utils_BR.isValidCPF(var_objDTO.getCpfOuCnpj())) {
			var_list.add(new REST_exceptionFieldMessage("CpfOuCnpj", "CPF inválido."));
		}

		if (var_objDTO.getTipoCliente().equals(enumTipoCliente.PESSOAJURIDICA.getCod())	&& !Service_Validation_Utils_BR.isValidCNPJ(var_objDTO.getCpfOuCnpj())) {
			var_list.add(new REST_exceptionFieldMessage("CpfOuCnpj", "CNPJ inválido."));
		}

		ClienteDomain auxCli = var_repoCliente.findByEmail(var_objDTO.getEmail());
		if ( auxCli != null  ) {
			var_list.add(new REST_exceptionFieldMessage("email", "Email já cadastrado."));
		}

		// a classe 'REST_exceptionFieldMessage' não pertence ao Framework do Spring,
		// mas o seu conteúdo (os possíveis erros que existirem) precisa ser transferido
		// para o Framework, por isso o 'for' abaixo
		for (REST_exceptionFieldMessage x : var_list) {
			// os erros aqui incluídos serão percebidos pelo Framework no tratamento de
			// erros criado em 'REST_exceptionResourceExceptionHandler' no tipo de
			// erro 'MethodArgumentNotValidException', mais especificamente no 'for' que
			// varre os campos 'FieldError' lá em 'REST_exceptionResourceExceptionHandler'
			var_context.disableDefaultConstraintViolation();
			var_context.buildConstraintViolationWithTemplate(x.getMessage()).addPropertyNode(x.getFieldName()).addConstraintViolation();
		}
		return var_list.isEmpty(); // se a lista de erros estiver 'vazia' retornará 'False', indicando que não há
								// erros de validação
	}
}
