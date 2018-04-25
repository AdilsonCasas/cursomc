package com.nelioalves.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.EstadoEntity;
import com.nelioalves.cursomc.domain.dto.DTO_Estado;
import com.nelioalves.cursomc.services.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class REST_EstadoResource {

	@Autowired
	public EstadoService var_serviceEstado;
	
// ============================= METODO GET: faz uma busca get/find no BD por uma instância da entidade que já existe no BD ======================================= 
	@RequestMapping(method=RequestMethod.GET) // GET para TODAS AS Estados, SEM paginação
	public ResponseEntity<List<DTO_Estado>> metodoREST_findAllEstado() {
		List<EstadoEntity> var_list = var_serviceEstado.metodoService_findAllEstado();
		List<DTO_Estado> var_listDto = var_list.stream().map(obj -> new DTO_Estado(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(var_listDto);
	}
}
