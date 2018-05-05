package com.nelioalves.cursomc.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nelioalves.cursomc.CursomcApplication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component // define um componente que pode ser injetado em outras classes.
public class JWT_Util {
	
	// as variáveis 'jwt' abaixo estão definidas no arq. 'application.properties'
	@Value("${jwt.segredo}")
	private String var_secret;

	@Value("${jwt.tempoexpira}")
	private Long var_expiration;
	
	public String generateToken(String var_username) {
		return Jwts.builder() /* este método cria um token a partir da palavra secreta + email usuário + tempo de expiração */
				.setSubject(var_username)
				.setExpiration(new Date(System.currentTimeMillis() + var_expiration))
				.signWith(SignatureAlgorithm.HS512, var_secret.getBytes()) /* escolhe qual o algoritmo usado na geração do token, o 'HS512' é um poderoso altoritmo de encryptação*/
				.compact();
	}
	
	public boolean tokenValido(String var_token) {
		if(CursomcApplication.desativarSeguranca.equals("SIM")) {
			return true;
		}

		// o 'Claims' abaixo é um tipo do JWT que armazena as reinvicações do token
		Claims var_claims = getClaims(var_token);
		if (var_claims != null) {
			String var_userName = var_claims.getSubject();
			Date var_expirationDate = var_claims.getExpiration();
			Date var_now = new Date(System.currentTimeMillis());
			if (var_userName != null && var_expirationDate != null && var_now.before(var_expirationDate)) {
				return true;
			}
		}
		return false;
	}
	
	public String getUserName(String var_token) {
		// o 'Claims' abaixo é um tipo do JWT que armazena as reinvicações do token
		Claims var_claims = getClaims(var_token);
		if (var_claims != null) {
			return(var_claims.getSubject());
		}
		return null;
	}

	private Claims getClaims(String var_token) {
		try {
			return Jwts.parser().setSigningKey(var_secret.getBytes()).parseClaimsJws(var_token).getBody();
		}
		catch (Exception e) {
			return null;
		}
	}
}