package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.domainPedido;
import com.nelioalves.cursomc.repositories.repositoryPedido;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class servicePedido {

	@Autowired
	private repositoryPedido repo;
	
	public domainPedido service_find(Integer Id) {
		Optional<domainPedido> obj = repo.findById(Id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + Id + ", Tipo: " + domainPedido.class.getName()));
	}	
	
}
