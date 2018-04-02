package com.nelioalves.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

	@Autowired
	public ClienteService service;
	
	@RequestMapping(value="/{Id}", method=RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer Id) {
		
		Cliente obj = service.find(Id);
		return ResponseEntity.ok().body(obj);
	}
}
