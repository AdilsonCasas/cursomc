package com.nelioalves.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc.domain.PedidoEntity;
import com.nelioalves.cursomc.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class REST_PedidoResource {

	@Autowired
	public PedidoService var_servicePedido;

// ============================= METODO GET: faz uma busca get/find no BD por uma instância da entidade que já existe no BD ======================================= 
	@RequestMapping(value="/{paramId}", method=RequestMethod.GET)
	public ResponseEntity<PedidoEntity> metodoREST_findPedido(@PathVariable Integer paramId) throws Exception {
		PedidoEntity var_obj = var_servicePedido.metodoService_findPedidoById(paramId);
		try {
		}
		catch (Exception e) {
			throw new Exception("ERRO_PADRAO#00??@Exception: "+e.getMessage())?;
		}
		return ResponseEntity.ok().body(var_obj);
	}

	@RequestMapping(method=RequestMethod.GET) // GET para TODAS os Pedidos, COM paginação
	public ResponseEntity<Page<PedidoEntity>> metodoREST_findPagePedido(
					@RequestParam(value="NumPage", defaultValue="0") Integer var_NumPage, 
					@RequestParam(value="LinesPerPage", defaultValue="24") Integer var_LinesPerPage, 
					@RequestParam(value="orderBy" , defaultValue="instante") String var_orderBy, 
					@RequestParam(value="directionOrderBy", defaultValue="DESC") String var_directionOrderBy) throws Exception {
		// os parâmetros do método 'resource_findPage' virão de parâmetros colocados na chamada do recurso na url do app chamador
		// ex1 de chamada: "http://localhost:8080/pedidos/page?NumPage=0&LinesPerPage=2&orderBy=nome&directionOrderBy=DESC" (NumPage=0, significa primeira página)
		// ex2 de chamada: "http://localhost:8080/pedidos/page" (sem parâmetros)
		// ex3 de chamada: "http://localhost:8080/pedidos/page?LinesPerPage=2" (somente parâmetro 'LinesPerPage' informado, o resto pega o default)
		Page<PedidoEntity> var_list = var_servicePedido.metodoService_findPagePedido(var_NumPage, var_LinesPerPage, var_orderBy, var_directionOrderBy);
		try {
		}
		catch (Exception e) {
			throw new Exception("ERRO_PADRAO#00??@Exception: "+e.getMessage())?;
		}
		return ResponseEntity.ok().body(var_list);
	}

// ============================= METODO POST: faz um "insert" de nova instância da entidade no BD ================================================================= 
	@RequestMapping(method=RequestMethod.POST)
	// a diretiva '@Valid' abaixo percebe/captura o resultado do método 'isValid' definido na classe 'serviceClienteInsertValidator'
	public ResponseEntity<Void> metodoREST_insertPedido(@Valid @RequestBody PedidoEntity var_obj) throws Exception {
		var_obj = var_servicePedido.metodoService_insertPedido(var_obj);
		URI var_uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(var_obj.getId()).toUri();
		try {
		}
		catch (Exception e) {
			throw new Exception("ERRO_PADRAO#00??@Exception: "+e.getMessage())?;
		}
		return ResponseEntity.created(var_uri).build();
	}
	
// ============================= METODO PUT: faz um "update" no BD em uma instância da entidade que já existe no BD ==============================================

// ============================= METODO DELETE: faz um "delete" no BD em uma instância da entidade que já existe no BD ============================================ 
		
}
