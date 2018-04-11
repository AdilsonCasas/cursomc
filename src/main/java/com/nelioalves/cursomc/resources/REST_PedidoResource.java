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

import com.nelioalves.cursomc.domain.PedidoDomain;
import com.nelioalves.cursomc.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class REST_PedidoResource {

	@Autowired
	public PedidoService var_servicePedido;
	
// ============================= METODO GET: faz uma busca get/find no BD por uma instância da entidade que já existe no BD ======================================= 
	@RequestMapping(value="/{paramId}", method=RequestMethod.GET)
	public ResponseEntity<PedidoDomain> metodoREST_findPedido(@PathVariable Integer paramId) {
		PedidoDomain var_obj = var_servicePedido.metodoService_findPedido(paramId);
		return ResponseEntity.ok().body(var_obj);
	}

// ============================= METODO POST: faz um "insert" de nova instância da entidade no BD ================================================================= 
	@RequestMapping(method=RequestMethod.POST)
	// a diretiva '@Valid' abaixo percebe/captura o resultado do método 'isValid' definido na classe 'serviceClienteInsertValidator'
	public ResponseEntity<Void> metodoREST_insertPedido(@Valid @RequestBody PedidoDomain var_obj) {
		var_obj = var_servicePedido.metodoService_insertPedido(var_obj);
		URI var_uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(var_obj.getId()).toUri();
		return ResponseEntity.created(var_uri).build();
	}
	
// ============================= METODO PUT: faz um "update" no BD em uma instância da entidade que já existe no BD ==============================================

// ============================= METODO DELETE: faz um "delete" no BD em uma instância da entidade que já existe no BD ============================================ 
		
}
