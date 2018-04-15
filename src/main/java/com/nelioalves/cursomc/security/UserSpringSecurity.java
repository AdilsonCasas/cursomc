package com.nelioalves.cursomc.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nelioalves.cursomc.domain.enums.enumPerfilUsuario;

/* abaixo os 2 exempls de JSon para autenticação no login do sistema --> teste de Spring Secutiry

Este aqui deve dar erro 'Não Autorizado'
{
	"email": "ana@gmail.com",
	"senha": "123"
}

o email --> pp890645@gmail.com deve dar certo a autenticação, o campo Headers do JSon retorna o token 'Bearer', para testar o sistema

faça uma requisição GET no Postman com o endpoint 'localhost:8080/pedidos' --> isso deve retornar um erro tipo 'Forbiden' (consulta não
autorizada), mas para autorizar vá no campo headers do GET no Postman, inclua a 'Key' 'Authorization' e no campo "value" da key
coloque o token gerado acima pela autenticação (copie desde 'Bearer' --> ex: 
'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcDg5MDY0NUBnbWFpbC5jb20iLCJleHAiOjE1MjM4MDI2NDd9.OUqUW1DQk4hOpcVvlUxhWcOzaNx-UOUWts5te0tAylGJHmuU5dPxEurWU-tHRCCHn3KfS9SX5cTVTqmCJ2KOZg'


 */
public class UserSpringSecurity implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private Integer Id;
	private String email;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserSpringSecurity() {
	}
	
	public UserSpringSecurity(Integer var_Id, String var_email, String var_senha, Set<enumPerfilUsuario> var_perfis) {
		super();
		this.Id = var_Id;
		this.email = var_email;
		this.senha = var_senha;
		this.authorities = var_perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}

	public Integer getId() {
		return this.Id;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}