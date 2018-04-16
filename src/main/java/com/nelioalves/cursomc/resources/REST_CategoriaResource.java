package com.nelioalves.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc.domain.CategoriaEntity;
import com.nelioalves.cursomc.domain.dto.DTO_CategoriaEntity;
import com.nelioalves.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class REST_CategoriaResource {

	@Autowired
	public CategoriaService var_serviceCategoria;
	
// ============================= METODO GET: faz uma busca get/find no BD por uma instância da entidade que já existe no BD ======================================= 
		@RequestMapping(value="/{paramId}", method=RequestMethod.GET) // GET para SOMENTE UMA Categoria
		public ResponseEntity<CategoriaEntity> metodoREST_findCategoria(@PathVariable Integer paramId) {
			CategoriaEntity var_obj = var_serviceCategoria.metodoService_findCategoria(paramId);
			return ResponseEntity.ok().body(var_obj);
		}
		
		@RequestMapping(method=RequestMethod.GET) // GET para TODAS AS Categorias, SEM paginação
		public ResponseEntity<List<DTO_CategoriaEntity>> metodoREST_findAllCategoria() {
			List<CategoriaEntity> var_list = var_serviceCategoria.metodoService_findAllCategoria();
			List<DTO_CategoriaEntity> var_listDto = var_list.stream().map(obj -> new DTO_CategoriaEntity(obj)).collect(Collectors.toList());
			return ResponseEntity.ok().body(var_listDto);
		}
		
		@RequestMapping(value="/page", method=RequestMethod.GET) // GET para TODAS AS Categorias, COM paginação
		public ResponseEntity<Page<DTO_CategoriaEntity>> metodoREST_findPageCategoria(
						@RequestParam(value="NumPage", defaultValue="0") Integer var_NumPage, 
						@RequestParam(value="LinesPerPage", defaultValue="24") Integer var_LinesPerPage, 
						@RequestParam(value="orderBy" , defaultValue="nome") String var_orderBy, 
						@RequestParam(value="directionOrderBy", defaultValue="ASC") String var_directionOrderBy) {
			// os parâmetros do método 'resource_findPage' virão de parâmetros colocados na chamada do recurso na url do app chamador
			// ex1 de chamada: "http://localhost:8080/categorias/page?NumPage=0&LinesPerPage=2&orderBy=nome&directionOrderBy=DESC" (NumPage=0, significa primeira página)
			// ex2 de chamada: "http://localhost:8080/categorias/page" (sem parâmetros)
			// ex3 de chamada: "http://localhost:8080/categorias/page?LinesPerPage=2" (somente parâmetro 'LinesPerPage' informado, o resto pega o default)
			Page<CategoriaEntity> var_list = var_serviceCategoria.metodoService_findPageCategoria(var_NumPage, var_LinesPerPage, var_orderBy, var_directionOrderBy);
			Page<DTO_CategoriaEntity> var_listDto = var_list.map(obj -> new DTO_CategoriaEntity(obj));
			return ResponseEntity.ok().body(var_listDto);
		}
		
// ============================= METODO POST: faz um "insert" de nova instância da entidade no BD =================================================================
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.POST)
	// a diretiva '@Valid' abaixo percebe/captura o resultado do método 'isValid' definido na classe 'serviceClienteInsertValidator'
	public ResponseEntity<Void> metodoREST_insertCategoria(@Valid @RequestBody DTO_CategoriaEntity var_objDTO) {
		CategoriaEntity var_obj = var_serviceCategoria.metodoService_fromDTO_to_Categoria(var_objDTO);
		var_obj = var_serviceCategoria.metodoService_insertCategoria(var_obj);
		URI var_uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(var_obj.getId()).toUri();
		return ResponseEntity.created(var_uri).build();
	}
	
// ============================= METODO PUT: faz um "update" no BD em uma instância da entidade que já existe no BD ============================================== 
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{paramId}", method=RequestMethod.PUT)
	// o "@Valid" abaixo é parte do "Bean Validate" que faz parte od Java EE, ele chama a validação definida nas diretivas incluídas no "domain" da categoria
	public ResponseEntity<Void> metodoREST_updateCategoria(@Valid @RequestBody DTO_CategoriaEntity var_objDTO, @PathVariable Integer paramId) {
		CategoriaEntity var_obj = var_serviceCategoria.metodoService_fromDTO_to_Categoria(var_objDTO);
		var_obj.setId(paramId);
		var_obj = var_serviceCategoria.metodoService_updateCategoria(var_obj);
		return ResponseEntity.noContent().build();
	}
	
// ============================= METODO DELETE: faz um "delete" no BD em uma instância da entidade que já existe no BD ============================================ 
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{paramId}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> metodoREST_deleteCategoria(@PathVariable Integer paramId) {
		var_serviceCategoria.metodoService_deleteCategoria(paramId);
		return ResponseEntity.noContent().build();
	}
	
}
