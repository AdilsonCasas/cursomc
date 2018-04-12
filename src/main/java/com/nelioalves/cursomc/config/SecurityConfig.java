package com.nelioalves.cursomc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// linha abaixo somente para liberar a consulta do "h2"
	@Autowired
	private Environment var_env;

	// vetor de endpoints liberados não apenas para readOlny, porém com alterações no bd autorizadas
	private static final String[] var_PUBLIC_MATCHERS = {
			"/h2-console/**"
	};
	
	// vetor de endpoints liberados APENAS para readOlny
	private static final String[] var_PUBLIC_MATCHERS_GET = {
			"/produtos/**",
			"/categorias/**",
			"/clientes/**"
	};
	
	@Override
	protected void configure(HttpSecurity var_http) throws Exception {
		// se os profiles do sistema tem "test" significa que eu estou tentando acessar o bd "h2"
		if(Arrays.asList(var_env.getActiveProfiles()).contains("test")) {
			var_http.headers().frameOptions().disable();
		}
		var_http.cors().and().csrf().disable();
		var_http.authorizeRequests()
				.antMatchers(HttpMethod.GET, var_PUBLIC_MATCHERS_GET).permitAll()
				.antMatchers(var_PUBLIC_MATCHERS).permitAll()
				.anyRequest().authenticated();
		var_http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Bean
	CorsConfigurationSource var_corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource var_source = new UrlBasedCorsConfigurationSource();
		var_source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return var_source;
	}
	
	@Bean
	public BCryptPasswordEncoder metodo_Config_bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
