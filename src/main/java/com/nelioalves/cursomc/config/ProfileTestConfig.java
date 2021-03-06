package com.nelioalves.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.nelioalves.cursomc.services.DBService;
import com.nelioalves.cursomc.services.EmailMockService;
import com.nelioalves.cursomc.services.EmailService;

@Configuration
// as definições dentro desta classe só terão efeito dentro do profile "test", veja o arq "application-test.properties"
@Profile("test")
public class ProfileTestConfig {

	@Autowired
	private DBService var_serviceDB;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		var_serviceDB.metodoService_instantiateTestDatabase();
		return true;
	}
	
	// o 'Mock' só vai existir no ambiente de 'test', por isso esta instanciação somente aqui neste profile para testes.
	@Bean
	public EmailService emailService() {
		return new EmailMockService();
	}

}
