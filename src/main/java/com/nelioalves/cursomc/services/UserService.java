package com.nelioalves.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.nelioalves.cursomc.security.UserSpringSecurity;

public class UserService {
	
	// Um método "public static" pode ser chamado independentemente da classe 'UserService' ser instanciada.
	public static UserSpringSecurity metodoService_authenticaded() {
		try {
			return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
