package com.nelioalves.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import com.nelioalves.cursomc.domain.domainCliente;
import com.nelioalves.cursomc.domain.dto.domainDTO_Cliente;
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
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> resource_insert(@Valid @RequestBody domainDTO_Cliente objDTO) {
		domainCliente obj = serviceCliente.service_fromDTO_to_Cliente(objDTO);
		obj = serviceCliente.service_insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{Id}", method=RequestMethod.PUT)
	// o "@Valid" abaixo é parte do "Bean Validate" que faz parte od Java EE, ele chama a validação definida nas diretivas incluídas no "domain" do cliente
	public ResponseEntity<Void> resource_update(@Valid @RequestBody domainDTO_Cliente objDTO, @PathVariable Integer Id) {
		domainCliente obj = serviceCliente.service_fromDTO_to_Cliente(objDTO);
		obj.setId(Id);
		obj = serviceCliente.service_update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{Id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> resource_delete(@PathVariable Integer Id) {
		serviceCliente.service_delete(Id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<domainDTO_Cliente>> resource_findAll() {
		List<domainCliente> list = serviceCliente.service_findAll();
		List<domainDTO_Cliente> listDto = list.stream().map(obj -> new domainDTO_Cliente(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	// os parâmetros do método 'resource_findPage' virão de parâmetros colocados na chamada do recurso na url do app chamador
	// ex1 de chamada: "http://localhost:8080/clientes/page?NumPage=0&LinesPerPage=2&orderBy=nome&directionOrderBy=DESC" (NumPage=0, significa primeira página)
	// ex2 de chamada: "http://localhost:8080/clientes/page" (sem parâmetros)
	// ex3 de chamada: "http://localhost:8080/clientes/page?LinesPerPage=2" (somente parâmetro 'LinesPerPage' informado, o resto pega o default)
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<domainDTO_Cliente>> resource_findPage(
					@RequestParam(value="NumPage", defaultValue="0") Integer NumPage, 
					@RequestParam(value="LinesPerPage", defaultValue="24") Integer LinesPerPage, 
					@RequestParam(value="orderBy" , defaultValue="nome") String orderBy, 
					@RequestParam(value="directionOrderBy", defaultValue="ASC") String directionOrderBy) {
		Page<domainCliente> list = serviceCliente.service_findPage(NumPage, LinesPerPage, orderBy, directionOrderBy);
		Page<domainDTO_Cliente> listDto = list.map(obj -> new domainDTO_Cliente(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
}
