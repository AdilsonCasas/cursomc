package com.nelioalves.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.nelioalves.cursomc.domain.domainCliente;
import com.nelioalves.cursomc.domain.dto.domainDTO_Cliente_Completo;
import com.nelioalves.cursomc.domain.enums.enumTipoCliente;
import com.nelioalves.cursomc.repositories.repositoryCliente;
import com.nelioalves.cursomc.resources.REST_exceptionFieldMessage;
import com.nelioalves.cursomc.services.validation.utils.BR;

public class serviceValidatorClienteInsert implements ConstraintValidator<serviceAnnotation_ClienteInsert, domainDTO_Cliente_Completo> {

	@Autowired
	private repositoryCliente repoCliente;

	@Override
	public void initialize(serviceAnnotation_ClienteInsert ann) {
	}

	@Override
	public boolean isValid(domainDTO_Cliente_Completo objDTO, ConstraintValidatorContext context) {
		List<REST_exceptionFieldMessage> list = new ArrayList<>();
		if (objDTO.getTipoCliente().equals(enumTipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDTO.getCpfOuCnpj())) {
			list.add(new REST_exceptionFieldMessage("CpfOuCnpj", "CPF inválido."));
		}

		if (objDTO.getTipoCliente().equals(enumTipoCliente.PESSOAJURIDICA.getCod())	&& !BR.isValidCNPJ(objDTO.getCpfOuCnpj())) {
			list.add(new REST_exceptionFieldMessage("CpfOuCnpj", "CNPJ inválido."));
		}

		domainCliente auxCli = repoCliente.findByEmail(objDTO.getEmail());
		if ( auxCli != null  ) {
			list.add(new REST_exceptionFieldMessage("email", "Email já cadastrado."));
		}

		// a classe 'REST_exceptionFieldMessage' não pertence ao Framework do Spring,
		// mas o seu conteúdo (os possíveis erros que existirem) precisa ser transferido
		// para o Framework, por isso o 'for' abaixo
		for (REST_exceptionFieldMessage x : list) {
			// os erros aqui incluídos serão percebidos pelo Framework no tratamento de
			// erros criado em 'REST_exceptionResourceExceptionHandler' no tipo de
			// erro 'MethodArgumentNotValidException', mais especificamente no 'for' que
			// varre os campos 'FieldError' lá em 'REST_exceptionResourceExceptionHandler'
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(x.getMessage()).addPropertyNode(x.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty(); // se a lista de erros estiver 'vazia' retornará 'False', indicando que não há
								// erros de validação
	}
}
