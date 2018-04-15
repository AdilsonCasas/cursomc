package com.nelioalves.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.ClienteDomain;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.security.UserSpringSecurity;

@Service
public class SecurityService_UserDetailsServiceImplementacao implements UserDetailsService {

	@Autowired
	private ClienteRepository var_repo;
	
	@Override
	public UserDetails loadUserByUsername(String var_email) throws UsernameNotFoundException {
		ClienteDomain cli = var_repo.findByEmail(var_email);
		if (cli == null) {
			throw new UsernameNotFoundException(var_email);
		}
		return new UserSpringSecurity(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfis());
	}
}