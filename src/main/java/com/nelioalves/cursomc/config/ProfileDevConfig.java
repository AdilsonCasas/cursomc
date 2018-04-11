package com.nelioalves.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.nelioalves.cursomc.services.DBService;
import com.nelioalves.cursomc.services.EmailService;
import com.nelioalves.cursomc.services.EmailSmtpService;

@Configuration
// as definições dentro desta classe só terão efeito dentro do profile "test", veja o arq "application-test.properties"
@Profile("dev")
public class ProfileDevConfig {

	@Autowired
	private DBService var_serviceDB;

	// Esta anotação '@Value' abaixo pega o valor da chave 'spring.jpa.hibernate.ddl-auto' definida no arquivo 'application-dev.properties' para verificar se deve ou não criar a base de dados no BD mysql
	// coloque ---> 'spring.jpa.hibernate.ddl-auto=create' no arq config se quiser CRIAR o BD ao inicializar esta aplicação ou 'none' no lugar de create para não fazer nada - não criar o BD
	// o valor da variavel de configuração será colocada dentro da variável local tipo String logo abaixo (no meu caso 'strategy').
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String var_strategy; // 'estratégia' de criação do BD

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		if(!"create".equals(var_strategy)) { // qualquer coisa diferente de "create" --> return.
			return false;
		}
		var_serviceDB.metodoService_instantiateTestDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new EmailSmtpService();
	}

}
