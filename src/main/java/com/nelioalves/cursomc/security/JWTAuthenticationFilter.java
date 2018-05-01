package com.nelioalves.cursomc.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nelioalves.cursomc.domain.dto.DTO_Credenciais;

// o 'extends' abaixo chamado 'UsernamePasswordAuthenticationFilter' é um padrão do Spring Security e ele intercepta o endpoint '/login'
// enviado pelo Front_End para fazer a autenticação do usuário.
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
    
    private JWT_Util jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager var_authenticationManager, JWT_Util var_jwtUtil) {
    	setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        this.authenticationManager = var_authenticationManager;
        this.jwtUtil = var_jwtUtil;
    }
	
	@Override
    public Authentication attemptAuthentication(HttpServletRequest var_req, HttpServletResponse var_res) throws AuthenticationException {

			DTO_Credenciais var_CredenciaisDTO=null;
			try {
				var_CredenciaisDTO = new ObjectMapper().readValue(var_req.getInputStream(), DTO_Credenciais.class);
			} catch (JsonParseException e) {
				throw new RuntimeException("ERRO_PADRAO#0018@RuntimeException (attemptAuthentication 1): "+e.getMessage());
			} catch (JsonMappingException e) {
				throw new RuntimeException("ERRO_PADRAO#0018@RuntimeException (attemptAuthentication 2): "+e.getMessage());
			} catch (IOException e) {
				throw new RuntimeException("ERRO_PADRAO#0018@RuntimeException (attemptAuthentication 3): "+e.getMessage());
			}
	
			// o token abaixo não é do JWT, mas sim do Spring Security
	        UsernamePasswordAuthenticationToken var_authToken = new UsernamePasswordAuthenticationToken(var_CredenciaisDTO.getEmail(), var_CredenciaisDTO.getSenha(), new ArrayList<>());
	        
	        Authentication var_auth = this.authenticationManager.authenticate(var_authToken);
	        return var_auth;
	}
	
	@Override
    protected void successfulAuthentication(HttpServletRequest var_req,
                                            HttpServletResponse var_res,
                                            FilterChain var_chain,
                                            Authentication var_auth) { // throws IOException, ServletException 
	
		String var_username = ((UserSpringSecurity) var_auth.getPrincipal()).getUsername();
        String var_token = this.jwtUtil.generateToken(var_username);
        var_res.addHeader("Authorization", "Bearer " + var_token);
        var_res.addHeader("access-control-expose-headers", "Authorization");
	}
	
	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {
		 
        @Override
        public void onAuthenticationFailure(HttpServletRequest var_request, HttpServletResponse var_response, AuthenticationException var_exception) throws IOException {
            var_response.setStatus(401);
            var_response.setContentType("application/json"); 
            try {
				var_response.getWriter().append(json());
			} catch (IOException e) {
				throw new IOException("ERRO_PADRAO#0019@IOException: "+e.getMessage());
			}
        }
        
        private String json() {
            long var_date = new Date().getTime();
            return "{\"timestamp\": " + var_date + ", "
                + "\"status\": 401, "
                + "\"error\": \"Não autorizado\", "
                + "\"message\": \"Email ou senha inválidos\", "
                + "\"path\": \"/login\"}";
        }
    }
}