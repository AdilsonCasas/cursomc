package com.nelioalves.cursomc.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	private JWT_Util jwtUtil;
	
	// Este campo será usado para recuperar o "username" de dentro to token cryptografado passado como argumento de autenticação do user
	// para depois fazer uma busca 'findByEmail' no nosso BD, e ver se o usuário existe e qual a senha dele.
	private UserDetailsService userDetailsService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWT_Util var_jwtUtil, UserDetailsService var_userDetailsService) {
		super(authenticationManager);
		
		this.jwtUtil = var_jwtUtil;
		this.userDetailsService = var_userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest var_request,
									HttpServletResponse var_response,
									FilterChain var_chain) throws IOException, ServletException { 
		// o param 'var_request' contém o token cryptografado da requisição do endpoint
		String var_header = var_request.getHeader("Authorization");
		
		// testa o cabeçalho 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcDg5MDY0NUBnbWFpbC5jb20iLCJleHAiOjE1MjM4MDI2NDd9.OUqUW1DQk4hOpcVvlUxhWcOzaNx-UOUWts5te0tAylGJHmuU5dPxEurWU-tHRCCHn3KfS9SX5cTVTqmCJ2KOZg'
		// o início 'Bearer ' é padrão do framework
		if(var_header != null && var_header.startsWith("Bearer ")) {
			// pega uma substring a partir do caracter numero 7 (desconta o inicio 'Bearer ') 
			UsernamePasswordAuthenticationToken var_auth = metodoSecurity_getAuthentication(var_header.substring(7));
			if (var_auth != null) {
				// liberar a pesquisa/consulta indicado pelo endpoint(GET ou POST ou ...)
				SecurityContextHolder.getContext().setAuthentication(var_auth);
			}
		}
		try {
			var_chain.doFilter(var_request, var_response);
		} catch (IOException | ServletException e) {
			throw new IOException("ERRO_PADRAO#0017@"+"IOException | ServletException: "+e.getMessage());
		}
	}

	private UsernamePasswordAuthenticationToken metodoSecurity_getAuthentication(String var_token) {
		if(this.jwtUtil.tokenValido(var_token)) {
			String var_userName =this.jwtUtil.metodoSecurity_getUserName(var_token); 
			UserDetails var_userDetails = this.userDetailsService.loadUserByUsername(var_userName);
			return new UsernamePasswordAuthenticationToken(var_userDetails, null, var_userDetails.getAuthorities());
		}
		return null;
	}

}
