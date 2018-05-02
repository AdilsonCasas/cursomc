package com.nelioalves.cursomc.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class FilterExposicaoDeHeader implements Filter {

	// Ao criar esta classe dá um erro apontado pelo framework e deve-se usar o autocorrigir do framework escolhendo a opção "Add unimplemented methods"
	// Esta classe "expõe um header" de uma requisição feita pelo backend, como um POST para criar uma nova caterogia, no Postman este header pode ser visto
	// no "Headers" resultado da criação de uma categoria, no campo "location". Isto também é feito na classe "JWTAuthenticationFilter" que expõe o header
	// "Authorization" com o token "Bearer" a ser usado no Postman
// =========================================== métodos incluídos automaticamente pelo framework spring ===================================================
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest par_request, ServletResponse par_response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse var_response = (HttpServletResponse) par_response;
        var_response.addHeader("access-control-expose-headers", "location");
        // esta função "doFilter" interrompe o ciclo normal da execução da requisição ao backend, captura o "ServeletResponse", depois este "chain" 
        // volta ao ciclo normal de execução, com novos valores, que foram transformados por este metodo.
        // Esta exposição deste header "location" se faz necessário para ser usado pelo "Angular" no frontend.
        chain.doFilter(par_request, var_response);
	}

	@Override
	public void destroy() {
	}

}
