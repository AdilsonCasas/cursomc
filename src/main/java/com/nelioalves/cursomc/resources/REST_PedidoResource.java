package com.nelioalves.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.domainPedido;
import com.nelioalves.cursomc.services.servicePedido;

@RestController
@RequestMapping(value="/pedidos")
public class REST_PedidoResource {

	@Autowired
	public servicePedido servicePedido;
	
	@RequestMapping(value="/{Id}", method=RequestMethod.GET)
	public ResponseEntity<domainPedido> resource_find(@PathVariable Integer Id) {
		domainPedido obj = servicePedido.service_find(Id);
		return ResponseEntity.ok().body(obj);
	}
}
