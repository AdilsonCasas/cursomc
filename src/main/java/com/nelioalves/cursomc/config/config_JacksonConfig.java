package com.nelioalves.cursomc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nelioalves.cursomc.domain.domainPagamentoComBoleto;
import com.nelioalves.cursomc.domain.domainPagamentoComCartao;

// Esta classe de 'configuração' contém informações que serão executadas no início da aplicação, é necessário ter '@Configuration' no início da classe e
// '@Bean' no corpo da implementação para indicar quais são estas configurações a serem processadas.
// Esta anotação é trabalhada em conjunto com a anotação '@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonT ypeInfo.As.PROPERTY, property = "@type")'
// incluída na classe 'domainPagamento' que é uma super-classe e não é instanciada diretamente, mas somente através das suas classes de herança.
@Configuration
public class config_JacksonConfig {
	// veja em 'https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-of-interfaceclass-without-hinting-the-pare' uma discussão sobre este tópico
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(domainPagamentoComCartao.class);
				objectMapper.registerSubtypes(domainPagamentoComBoleto.class);
				super.configure(objectMapper);
			}
		};
		return builder;
	}
}
