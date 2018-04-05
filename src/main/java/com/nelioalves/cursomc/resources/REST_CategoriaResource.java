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

import com.nelioalves.cursomc.domain.domainCategoria;
import com.nelioalves.cursomc.domain.dto.domainDTO_Categoria;
import com.nelioalves.cursomc.services.serviceCategoria;

@RestController
@RequestMapping(value="/categorias")
public class REST_CategoriaResource {

	@Autowired
	public serviceCategoria serviceCategoria;
	
// ============================= METODO GET: faz uma busca get/find no BD por uma instância da entidade que já existe no BD ======================================= 
		@RequestMapping(value="/{Id}", method=RequestMethod.GET) // GET para SOMENTE UMA Categoria
		public ResponseEntity<domainCategoria> resource_find(@PathVariable Integer Id) {
			domainCategoria obj = serviceCategoria.service_find(Id);
			return ResponseEntity.ok().body(obj);
		}
		
		@RequestMapping(method=RequestMethod.GET) // GET para TODAS AS Categorias, SEM paginação
		public ResponseEntity<List<domainDTO_Categoria>> resource_findAll() {
			List<domainCategoria> list = serviceCategoria.service_findAll();
			List<domainDTO_Categoria> listDto = list.stream().map(obj -> new domainDTO_Categoria(obj)).collect(Collectors.toList());
			return ResponseEntity.ok().body(listDto);
		}
		
		@RequestMapping(value="/page", method=RequestMethod.GET) // GET para TODAS AS Categorias, COM paginação
		public ResponseEntity<Page<domainDTO_Categoria>> resource_findPage(
						@RequestParam(value="NumPage", defaultValue="0") Integer NumPage, 
						@RequestParam(value="LinesPerPage", defaultValue="24") Integer LinesPerPage, 
						@RequestParam(value="orderBy" , defaultValue="nome") String orderBy, 
						@RequestParam(value="directionOrderBy", defaultValue="ASC") String directionOrderBy) {
			// os parâmetros do método 'resource_findPage' virão de parâmetros colocados na chamada do recurso na url do app chamador
			// ex1 de chamada: "http://localhost:8080/categorias/page?NumPage=0&LinesPerPage=2&orderBy=nome&directionOrderBy=DESC" (NumPage=0, significa primeira página)
			// ex2 de chamada: "http://localhost:8080/categorias/page" (sem parâmetros)
			// ex3 de chamada: "http://localhost:8080/categorias/page?LinesPerPage=2" (somente parâmetro 'LinesPerPage' informado, o resto pega o default)
			Page<domainCategoria> list = serviceCategoria.service_findPage(NumPage, LinesPerPage, orderBy, directionOrderBy);
			Page<domainDTO_Categoria> listDto = list.map(obj -> new domainDTO_Categoria(obj));
			return ResponseEntity.ok().body(listDto);
		}
		
// ============================= METODO POST: faz um "insert" de nova instância da entidade no BD ================================================================= 
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> resource_insert(@Valid @RequestBody domainDTO_Categoria objDTO) {
		domainCategoria obj = serviceCategoria.service_fromDTO_to_Categoria(objDTO);
		obj = serviceCategoria.service_insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
// ============================= METODO PUT: faz um "update" no BD em uma instância da entidade que já existe no BD ============================================== 
	@RequestMapping(value="/{Id}", method=RequestMethod.PUT)
	// o "@Valid" abaixo é parte do "Bean Validate" que faz parte od Java EE, ele chama a validação definida nas diretivas incluídas no "domain" da categoria
	public ResponseEntity<Void> resource_update(@Valid @RequestBody domainDTO_Categoria objDTO, @PathVariable Integer Id) {
		domainCategoria obj = serviceCategoria.service_fromDTO_to_Categoria(objDTO);
		obj.setId(Id);
		obj = serviceCategoria.service_update(obj);
		return ResponseEntity.noContent().build();
	}
	
// ============================= METODO DELETE: faz um "delete" no BD em uma instância da entidade que já existe no BD ============================================ 
	@RequestMapping(value="/{Id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> resource_delete(@PathVariable Integer Id) {
		serviceCategoria.service_delete(Id);
		return ResponseEntity.noContent().build();
	}
	
}
