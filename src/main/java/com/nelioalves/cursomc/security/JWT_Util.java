package com.nelioalves.cursomc.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component // define um componente que pode ser injetado em outras classes.
public class JWT_Util {
	
	// as duas variáveis abaixo estão definidas no arq. 'application.properties'
	@Value("${jwt.segredo}")
	private String var_secret;

	@Value("${jwt.tempoexpira}")
	private Long var_expiration;
	
	public String generateToken(String var_username) {
		return Jwts.builder()
				.setSubject(var_username)
				.setExpiration(new Date(System.currentTimeMillis() + var_expiration))
				.signWith(SignatureAlgorithm.HS512, var_secret.getBytes())
				.compact();
	}
}