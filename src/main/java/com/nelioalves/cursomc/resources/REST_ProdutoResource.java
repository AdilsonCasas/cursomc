package com.nelioalves.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.ProdutoDomain;
import com.nelioalves.cursomc.domain.dto.DTO_ProdutoDomain;
import com.nelioalves.cursomc.resources.utils.REST_Utils_URL;
import com.nelioalves.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class REST_ProdutoResource {

	@Autowired
	public ProdutoService serviceProduto;
	
// ============================= METODO GET: faz uma busca get/find no BD por uma instância da entidade que já existe no BD ======================================= 
		@RequestMapping(value="/{Id}", method=RequestMethod.GET) // GET para SOMENTE UM Produto
		public ResponseEntity<ProdutoDomain> resource_find(@PathVariable Integer Id) {
			ProdutoDomain obj = serviceProduto.service_find(Id);
			return ResponseEntity.ok().body(obj);
		}
	
		@RequestMapping(method=RequestMethod.GET) // Get para TODOS os Produtos, COM paginação
		public ResponseEntity<Page<DTO_ProdutoDomain>> resource_findPage(
						@RequestParam(value="nome", defaultValue="") String nome, 
						@RequestParam(value="categorias", defaultValue="") String categorias, 
						@RequestParam(value="NumPage", defaultValue="0") Integer NumPage, 
						@RequestParam(value="LinesPerPage", defaultValue="24") Integer LinesPerPage, 
						@RequestParam(value="orderBy" , defaultValue="nome") String orderBy, 
						@RequestParam(value="directionOrderBy", defaultValue="ASC") String directionOrderBy) {

			// a URL de chamada deste GET terá o seguinte formato: 'http://localhost:8080/produtos/nome=computador&categorias=1,3,4'
			// observe as categorias separadas por vírgulas
			// esta URL deve ser desmembrada para recuperar os parâmetros a serem enviados para o método 'search'
			List <Integer> ids = REST_Utils_URL.REST_utils_decodeIntList(categorias);

			// o parâmetro 'nome' pode estar no formato típico de uma URL, que tenha passado por um'encode' que transforma uma string com possíveis espaços em banco, 
			// para uma string do formato que existe dentro das URL's, por ex "TV LED" --> "TV%20LED".
			// O método 'REST_utils_decodeParam' descodifica a string no parâmetro
			String nomeDecoded = REST_Utils_URL.REST_utils_decodeParam(nome);
		
			Page<ProdutoDomain> list = serviceProduto.service_search(nomeDecoded, ids, NumPage, LinesPerPage, orderBy, directionOrderBy);
			Page<DTO_ProdutoDomain> listDto = list.map(obj -> new DTO_ProdutoDomain(obj));
			return ResponseEntity.ok().body(listDto);
		}
		
// ============================= METODO POST: faz um "insert" de nova instância da entidade no BD ================================================================= 
	
// ============================= METODO PUT: faz um "update" no BD em uma instância da entidade que já existe no BD ============================================== 
	
// ============================= METODO DELETE: faz um "delete" no BD em uma instância da entidade que já existe no BD ============================================ 
	
}
