package com.nelioalves.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.nelioalves.cursomc.services.DBService;

@Configuration
// as definições dentro desta classe só terão efeito dentro do profile "test", veja o arq "application-test.properties"
@Profile("test")
public class ProfileTestConfig {

	@Autowired
	private DBService serviceDB;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		serviceDB.instantiateTestDatabase();
		return true;
	}

}
