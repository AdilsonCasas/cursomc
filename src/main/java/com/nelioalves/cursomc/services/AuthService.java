package com.nelioalves.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.ClienteEntity;
import com.nelioalves.cursomc.repositories.ClienteRepository;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository var_repoCliente;
	
	@Autowired
	private BCryptPasswordEncoder var_bCryptPasswordEncoder;

	@Autowired
	// a var 'serviceEmail' abaixo é instanciada no arq. de configuração 'ProfileTestConfig', sempre que uso um '@Autowired' há uma instanciação do componente do sistema citado.
	// como 'EmailService' é uma interface e não uma classe é necessário a instanciação por 'new'
	private EmailService var_serviceEmail;
	
	private Random var_rand = new Random(); // 'Random()' é uma classe do java que gera caracteres aleatórios

	public void metodoService_sendNewPassword(String var_email) throws Exception {
		ClienteEntity var_Cliente = null;
		try {
			var_Cliente = var_repoCliente.metodoRepo_findClienteByEmail(var_email);
		}
		catch (Exception e) {
			throw new Exception("ERRO_PADRAO#0053@Exception: "+e.getMessage());
		}
		if (var_Cliente == null) {
			throw new Exception("ERRO_PADRAO#0005@"+"xiiiiiiii...");
		}

		String var_newPass = metodoService_newPassword();
		var_Cliente.setSenha(var_bCryptPasswordEncoder.encode(var_newPass));
		var_repoCliente.save(var_Cliente);
		var_serviceEmail.metodoService_sendNewPasswordEmail(var_Cliente, var_newPass);
	}

	private String metodoService_newPassword() {
		char[] var_vet = new char[10]; // uma senha qualquer de 10 caracteres
		for (int i=0; i< 10; i++) {
			var_vet[i] = metodoService_randomChar();
		}
		return new String(var_vet);
	}

	private char metodoService_randomChar() {
		int opt = var_rand.nextInt(3); // gera 0 ou 1 ou 2
		if (opt == 0) { // gera um dígito
			return (char) (var_rand.nextInt(10) + 48); // tabela ASCII 48 = '0'
		}
		else if (opt == 1) { // gera uma letra maiuscula
			return (char) (var_rand.nextInt(26) + 65);
		}
		else { // gera letra minuscula
			return (char) (var_rand.nextInt(26) + 97);
		}
	}
}
