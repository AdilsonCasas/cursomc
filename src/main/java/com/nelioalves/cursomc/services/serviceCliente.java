package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.domainCliente;
import com.nelioalves.cursomc.repositories.repositoryCliente;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class serviceCliente {

	@Autowired
	private repositoryCliente repo;
	
	public domainCliente service_find(Integer Id) {
		Optional<domainCliente> obj = repo.findById(Id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + Id + ", Tipo: " + domainCliente.class.getName()));
	}	
	
}
