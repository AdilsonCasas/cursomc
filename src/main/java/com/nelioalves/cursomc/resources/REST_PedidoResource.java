package com.nelioalves.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc.domain.domainPedido;
import com.nelioalves.cursomc.services.servicePedido;

@RestController
@RequestMapping(value="/pedidos")
public class REST_PedidoResource {

	@Autowired
	public servicePedido servicePedido;
	
// ============================= METODO GET: faz uma busca get/find no BD por uma instância da entidade que já existe no BD ======================================= 
	@RequestMapping(value="/{Id}", method=RequestMethod.GET)
	public ResponseEntity<domainPedido> resource_find(@PathVariable Integer Id) {
		domainPedido obj = servicePedido.service_find(Id);
		return ResponseEntity.ok().body(obj);
	}

// ============================= METODO POST: faz um "insert" de nova instância da entidade no BD ================================================================= 
	@RequestMapping(method=RequestMethod.POST)
	// a diretiva '@Valid' abaixo percebe/captura o resultado do método 'isValid' definido na classe 'serviceClienteInsertValidator'
	public ResponseEntity<Void> resource_insert(@Valid @RequestBody domainPedido obj) {
	obj = servicePedido.service_insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
// ============================= METODO PUT: faz um "update" no BD em uma instância da entidade que já existe no BD ==============================================

// ============================= METODO DELETE: faz um "delete" no BD em uma instância da entidade que já existe no BD ============================================ 
		
}
