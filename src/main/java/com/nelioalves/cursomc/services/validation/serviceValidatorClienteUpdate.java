package com.nelioalves.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.nelioalves.cursomc.domain.domainCliente;
import com.nelioalves.cursomc.domain.dto.domainDTO_Cliente_nome_email;
import com.nelioalves.cursomc.repositories.repositoryCliente;
import com.nelioalves.cursomc.resources.REST_exceptionFieldMessage;

public class serviceValidatorClienteUpdate implements ConstraintValidator<serviceAnnotation_ClienteUpdate, domainDTO_Cliente_nome_email> {

	@Autowired
	private HttpServletRequest requestURI;

	@Autowired
	private repositoryCliente repoCliente;

	@Override
	public void initialize(serviceAnnotation_ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(domainDTO_Cliente_nome_email objDTO, ConstraintValidatorContext context) {

		// Este "Map" é padrão Java.util e é uma coleção de pares 'chave/valor' tipo o arq. JSon
		// Neste caso vamos usá-lo para pegar o "Id" da entidade a ser alterada que está na URL do PUT no Postman
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) requestURI.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("Id"));

		List<REST_exceptionFieldMessage> list = new ArrayList<>();
		domainCliente auxCli = repoCliente.findByEmail(objDTO.getEmail());
		if ( auxCli != null && !auxCli.getId().equals(uriId)  ) { /// este if verifica se a alteração no BD tenta alterar um email que já existe em outro cliente que não o mesmo pesquisado
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
