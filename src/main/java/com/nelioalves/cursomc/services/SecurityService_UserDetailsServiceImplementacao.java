package com.nelioalves.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.CursomcApplication;
import com.nelioalves.cursomc.domain.ClienteEntity;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.security.UserSpringSecurity;

@Service
public class SecurityService_UserDetailsServiceImplementacao implements UserDetailsService {

	@Autowired
	private ClienteRepository var_repo;
	
	@Override
	public UserDetails loadUserByUsername(String var_email) {
		if(CursomcApplication.desativarSeguranca.equals("SIM")) {
			var_email = "pp890645@gmail.com";
		}

		ClienteEntity var_cli = null;
		try {
			var_cli = var_repo.metodoRepo_findClienteByEmail(var_email);
		}
		catch (Exception e) {
			try {
				throw new Exception("ERRO_PADRAO#0056@Exception: "+e.getMessage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
			}
		}

		if (var_cli == null) {
			throw new UsernameNotFoundException("ERRO_PADRAO#0016@"+var_email);
		}

		return new UserSpringSecurity(var_cli.getId(), var_cli.getEmail(), var_cli.getSenha(), var_cli.getPerfis());
	}
}