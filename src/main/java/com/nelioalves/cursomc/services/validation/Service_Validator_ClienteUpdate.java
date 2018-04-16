package com.nelioalves.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.nelioalves.cursomc.domain.ClienteEntity;
import com.nelioalves.cursomc.domain.dto.DTO_ClienteEntity_nome_email;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.resources.exception.REST_exceptionFieldMessage;

public class Service_Validator_ClienteUpdate implements ConstraintValidator<Service_Annotation_ClienteUpdate, DTO_ClienteEntity_nome_email> {

	@Autowired
	private HttpServletRequest var_requestURI;

	@Autowired
	private ClienteRepository var_repoCliente;

	@Override
	public void initialize(Service_Annotation_ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(DTO_ClienteEntity_nome_email var_objDTO, ConstraintValidatorContext var_context) {

		// Este "Map" é padrão Java.util e é uma coleção de pares 'chave/valor' tipo o arq. JSon
		// Neste caso vamos usá-lo para pegar o "Id" da entidade a ser alterada que está na URL do PUT no Postman
		@SuppressWarnings("unchecked")
		Map<String, String> var_map = (Map<String, String>) var_requestURI.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer var_uriId = Integer.parseInt(var_map.get("Id"));

		List<REST_exceptionFieldMessage> var_list = new ArrayList<>();
		ClienteEntity var_auxCli = var_repoCliente.findByEmail(var_objDTO.getEmail());
		if ( var_auxCli != null && !var_auxCli.getId().equals(var_uriId)  ) { /// este if verifica se a alteração no BD tenta alterar um email que já existe em outro cliente que não o mesmo pesquisado
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
