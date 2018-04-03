package com.nelioalves.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.domainCliente;
import com.nelioalves.cursomc.services.serviceCliente;

@RestController
@RequestMapping(value="/clientes")
public class REST_ClienteResource {

	@Autowired
	public serviceCliente serviceCliente;
	
	@RequestMapping(value="/{Id}", method=RequestMethod.GET)
	public ResponseEntity<domainCliente> resource_find(@PathVariable Integer Id) {
		
		domainCliente obj = serviceCliente.service_find(Id);
		return ResponseEntity.ok().body(obj);
	}
}
