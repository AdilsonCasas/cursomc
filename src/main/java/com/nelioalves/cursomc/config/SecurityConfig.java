package com.nelioalves.cursomc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.nelioalves.cursomc.security.JWTAuthenticationFilter;
import com.nelioalves.cursomc.security.JWTAuthorizationFilter;
import com.nelioalves.cursomc.security.JWT_Util;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// linha abaixo somente para liberar a consulta do "h2"
	@Autowired
	private Environment var_env;

	@Autowired
	private JWT_Util var_jwtUtil;

	// vetor de endpoints liberados não apenas para readOlny, mas também com alterações no bd autorizadas
	private static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**"
	};

	// Perceba que o 'UserDetailsService' abaixo é uma inteface definida em 'org.springframework.security.core.userdetails.UserDetailsService'
	// e o Spring é inteligente o suficiente para procurar aqui no próprio sistema uma imprementação para esta interface e encontra esta 
	// imprementação em 'SecurityService_UserDetailsServiceImplementacao', então a variável 'var_userDetailsService' aponta para a nossa
	// implementação em 'SecurityService_UserDetailsServiceImplementacao'
	@Autowired
	private UserDetailsService userDetailsService;

	// vetor de endpoints liberados APENAS para readOlny (GET), independentemente de o usuário estar logado no sistema ou não
	private static final String[] PUBLIC_MATCHERS_GET = {
			"/produtos/**",
			"/categorias/**",
			"/estados/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_POST = {
			"/clientes/**",
//			"/clientes/picture/**", // se validar esta linha todo POST no endpoint '/clientes/picture' é autorizado, se tirar esta linha daqui uma autenticação de usuário será requerida 
			"/auth/forgot/**" 		// email com nova senha quando o cliente "esqueci a senha"
	};
	
	@Override
	protected void configure(HttpSecurity var_http) throws Exception {
		// se os profiles do sistema tem "test" significa que eu estou tentando acessar o bd "h2"
		if(Arrays.asList(var_env.getActiveProfiles()).contains("test")) {
			try {
				var_http.headers().frameOptions().disable(); // este comando desabilita proteção sobre o BD h2 (em ambiente de 'test')
			} catch (Exception e) {
				throw new Exception("ERRO_PADRAO#0021@Exception: "+e.getMessage());
			}
		}
	try {
			var_http.cors() /* habilita o "cors" básico executado pelo método 'corsConfigurationSource()' abaixo*/
						.and()
							.csrf().disable(); /* desabilita proteção contra atques tipo CSRF porque nossa aplicação backend é do tipo 'Stateless' (não armazena sessão)*/
		} catch (Exception e) {
			throw new Exception("ERRO_PADRAO#0022@Exception: "+e.getMessage());
		}
		try {
			var_http.authorizeRequests()
						.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
						.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
						.antMatchers(PUBLIC_MATCHERS).permitAll()
						.anyRequest().authenticated()
						;
		} catch (Exception e) {
			throw new Exception("ERRO_PADRAO#0023@Exception: "+e.getMessage());
		}
		try {
			var_http.addFilter(new JWTAuthenticationFilter(authenticationManager(), var_jwtUtil));
			var_http.addFilter(new JWTAuthorizationFilter(authenticationManager(), var_jwtUtil, userDetailsService));
			var_http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); /* explicita que o nosso API backend será do tipo STATELESS, ou seja, não vai criar sessão de usuário */
		} catch (Exception e) {
			throw new Exception("ERRO_PADRAO#0024@Exception: "+e.getMessage());
		}
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder var_auth) throws Exception {
		try {
			var_auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
		} catch (Exception e) {
			throw new Exception("ERRO_PADRAO#0025@Exception: "+e.getMessage());
		}
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration var_corsConfiguration = new CorsConfiguration();
		var_corsConfiguration.applyPermitDefaultValues();
		var_corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
		var_corsConfiguration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS")); // adiciona os métodos que serão permitidos no 'Cors'
		final UrlBasedCorsConfigurationSource var_source = new UrlBasedCorsConfigurationSource();
		var_source.registerCorsConfiguration("/**", var_corsConfiguration);
		return var_source;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
