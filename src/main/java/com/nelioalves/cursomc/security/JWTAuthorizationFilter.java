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

import com.nelioalves.cursomc.CursomcApplication;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	// as variáveis 'jwt' abaixo estão definidas no arq. 'application.properties'
//	@Value("${jwt.desativar.seguranca}") // var criada por Adilson para desabilitar momentaneamente a segurança para testes
//	private String var_desativarSeguranca;

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
	// um "filtro" no framework é um cara que INTERCEPTA uma requisição, faz algumas transformações nesta requisição e depois devolve o controle da execução
	// para o framework com a requisição transformada.
	protected void doFilterInternal(HttpServletRequest var_request,
									HttpServletResponse var_response,
									FilterChain var_chain) throws IOException, ServletException { 
		String var_header = null;
		if(CursomcApplication.desativarSeguranca.equals("SIM")) {
			// seta um token qualquer, só pra não deixar a var vazia...
			var_header = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcDg5MDY0NUBnbWFpbC5jb20iLCJleHAiOjE1MjU2MzkyMjB9.prs109Jv5XMGM6MPTTtv4TytL6IStXenId24n6ICyB1GUWfA0OutHRmupn4_BPJHPUg6lk3Ha8RbnZ8bEiZdTg";
		}
		else {
			var_header = var_request.getHeader("Authorization");
/*
			if(var_header == null) { // se não for passado nenhum token de login válido, pode simplesmente retornar porque o backend vai filtrar o que pode ser
									 // mostrado de outras formas, através dos campos 'PUBLIC_MATCHERS_' presentes no arq. 'SecurityConfig', ou seja,
									 // como política de regras de negócio existem duas formas de usar este sistema de backend:
									 //    1) quando o usuário está logado, tendo informado email+senha.
									 //    2) quando o usuário não está logado, (var_header == null), mas mesmo assim é possível consultar algumas coisas no
									 //       sistema, como por exemplo 'consultar categorias de produto', isto é uma regra de negócio estabelecida.
				return;
			}
 */
		}
		
		// o param 'var_request' contém o token cryptografado da requisição do endpoint
		// testa o cabeçalho 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcDg5MDY0NUBnbWFpbC5jb20iLCJleHAiOjE1MjM4MDI2NDd9.OUqUW1DQk4hOpcVvlUxhWcOzaNx-UOUWts5te0tAylGJHmuU5dPxEurWU-tHRCCHn3KfS9SX5cTVTqmCJ2KOZg'
		// o início 'Bearer ' é padrão do framework
		if(var_header != null && !var_header.startsWith("Bearer ")) {
			throw new ServletException("ERRO_PADRAO#0031@");
		}
		if(var_header != null && var_header.startsWith("Bearer ")) {
			// pega uma substring a partir do caracter numero 7 (desconta o inicio 'Bearer ') 
			UsernamePasswordAuthenticationToken var_auth = getAuthentication(var_header.substring(7));
			if (var_auth != null) { // Não Autorizado pois encontrou algum problema com o token passado como parametro deste metodo
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

	private UsernamePasswordAuthenticationToken getAuthentication(String var_token) {
		if(this.jwtUtil.tokenValido(var_token)) {
			String var_userName =this.jwtUtil.getUserName(var_token); 
			UserDetails var_userDetails = this.userDetailsService.loadUserByUsername(var_userName);
			return new UsernamePasswordAuthenticationToken(var_userDetails, null, var_userDetails.getAuthorities());
		}
		return null;
	}

}
