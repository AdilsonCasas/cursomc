package com.nelioalves.cursomc.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.dto.DTO_EmailForgotSenha;
import com.nelioalves.cursomc.security.JWT_Util;
import com.nelioalves.cursomc.security.UserSpringSecurity;
import com.nelioalves.cursomc.services.AuthService;
import com.nelioalves.cursomc.services.UserService;

@RestController
@RequestMapping(value = "/auth")
public class REST_AuthResource {
	// Esta classe é usada para fazer um "auto refresh" na autorização do cliente,
	// caso esteja próximo de expirar o token e o cliente ainda está
	// usando o site.
	
	// Nota: por que usamos POST e não GET, PUT ou DELETE?
	// - GET, PUT e DELETE devem ser usados para operações IDEMPOTENTES. Uma operação idempotente é aquela
	// que, se usada mais de uma vez, produz o mesmo resultado se usada uma única vez.

	@Autowired
	private JWT_Util jwt_util;

	@Autowired
	private AuthService serviceAuth;

	// endpoint para qdo o cliente ainda estiver interagindo com o site, mas o token dele está expirando
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> metodoService_refreshToken(HttpServletResponse var_response) {
		UserSpringSecurity var_user = UserService.metodoService_authenticaded();
		String var_token = this.jwt_util.generateToken(var_user.getUsername());
		var_response.addHeader("Authorization", "Bearer " + var_token);
        var_response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}

	// endpoint para "esqueci minha senha". o método 'forgotSenha' deve receber um 'request body' então deve-se criar um DTO pra auxiliar com isso
	// para testar coloque uma solicitação POST no Postman com o endereço 'localhost:8080/auth/forgot' e no Body da solicitação coloque
	//{
	//	"email": "pp890645@gmail.com"
	//}
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> metodoService_forgotSenha(@Valid @RequestBody DTO_EmailForgotSenha var_objDTO) throws Exception {
		serviceAuth.metodoService_sendNewPassword(var_objDTO.getEmail());
		return ResponseEntity.noContent().build();
	}
}
