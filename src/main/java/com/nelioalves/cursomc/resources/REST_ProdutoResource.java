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
	public ProdutoService var_serviceProduto;
	
// ============================= METODO GET: faz uma busca get/find no BD por uma instância da entidade que já existe no BD ======================================= 
		@RequestMapping(value="/{paramId}", method=RequestMethod.GET) // GET para SOMENTE UM Produto
		public ResponseEntity<ProdutoDomain> metodoREST_findProduto(@PathVariable Integer paramId) {
			ProdutoDomain var_obj = var_serviceProduto.metodoService_findProduto(paramId);
			return ResponseEntity.ok().body(var_obj);
		}
	
		@RequestMapping(method=RequestMethod.GET) // Get para TODOS os Produtos, COM paginação
		public ResponseEntity<Page<DTO_ProdutoDomain>> metodoREST_findPageProduto(
						@RequestParam(value="nome", defaultValue="") String var_nome, 
						@RequestParam(value="categorias", defaultValue="") String var_categorias, 
						@RequestParam(value="NumPage", defaultValue="0") Integer var_NumPage, 
						@RequestParam(value="LinesPerPage", defaultValue="24") Integer var_LinesPerPage, 
						@RequestParam(value="orderBy" , defaultValue="nome") String var_orderBy, 
						@RequestParam(value="directionOrderBy", defaultValue="ASC") String var_directionOrderBy) {

			// a URL de chamada deste GET terá o seguinte formato: 'http://localhost:8080/produtos/nome=computador&categorias=1,3,4'
			// observe as categorias separadas por vírgulas
			// esta URL deve ser desmembrada para recuperar os parâmetros a serem enviados para o método 'search'
			List <Integer> var_ids = REST_Utils_URL.metodoREST_utils_decodeIntList(var_categorias);

			// o parâmetro 'nome' pode estar no formato típico de uma URL, que tenha passado por um'encode' que transforma uma string com possíveis espaços em banco, 
			// para uma string do formato que existe dentro das URL's, por ex "TV LED" --> "TV%20LED".
			// O método 'REST_utils_decodeParam' descodifica a string no parâmetro
			String var_nomeDecoded = REST_Utils_URL.metodoREST_utils_decodeParam(var_nome);
		
			Page<ProdutoDomain> var_list = var_serviceProduto.metodoService_searchProduto(var_nomeDecoded, var_ids, var_NumPage, var_LinesPerPage, var_orderBy, var_directionOrderBy);
			Page<DTO_ProdutoDomain> var_listDto = var_list.map(var_obj -> new DTO_ProdutoDomain(var_obj));
			return ResponseEntity.ok().body(var_listDto);
		}
		
// ============================= METODO POST: faz um "insert" de nova instância da entidade no BD ================================================================= 
	
// ============================= METODO PUT: faz um "update" no BD em uma instância da entidade que já existe no BD ============================================== 
	
// ============================= METODO DELETE: faz um "delete" no BD em uma instância da entidade que já existe no BD ============================================ 
	
}
