package com.nelioalves.cursomc.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.security.JWT_Util;
import com.nelioalves.cursomc.security.UserSpringSecurity;
import com.nelioalves.cursomc.services.UserService;

@RestController
@RequestMapping(value = "/auth")
public class REST_AuthResource {
	// Esta classe é usada para fazer um "auto refresh" na autorização do cliente,
	// caso esteja próximo de expirar o token e o cliente ainda está
	// usando o site.
	
	@Autowired
	private JWT_Util jwt_util;

	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse var_response) {
		UserSpringSecurity var_user = UserService.metodoService_authenticaded();
		String var_token = this.jwt_util.generateToken(var_user.getUsername());
		var_response.addHeader("Authorization", "Bearer " + var_token);
		return ResponseEntity.noContent().build();
	}
}
