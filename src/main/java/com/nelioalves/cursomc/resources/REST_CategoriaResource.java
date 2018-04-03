package com.nelioalves.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc.domain.domainCategoria;
import com.nelioalves.cursomc.dto.CategoriaDTO;
import com.nelioalves.cursomc.services.serviceCategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class REST_CategoriaResource {

	@Autowired
	public serviceCategoriaService serviceCategoria;
	
	@RequestMapping(value="/{Id}", method=RequestMethod.GET)
	public ResponseEntity<domainCategoria> resource_find(@PathVariable Integer Id) {
		domainCategoria obj = serviceCategoria.service_find(Id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> resource_insert(@RequestBody domainCategoria obj) {
		obj = serviceCategoria.service_insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{Id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> resource_update(@RequestBody domainCategoria obj, @PathVariable Integer Id) {
		obj.setId(Id);
		obj = serviceCategoria.service_update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{Id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> resource_delete(@PathVariable Integer Id) {
		serviceCategoria.service_delete(Id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> resource_findAll() {
		List<domainCategoria> list = serviceCategoria.service_findAll();
		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
}
