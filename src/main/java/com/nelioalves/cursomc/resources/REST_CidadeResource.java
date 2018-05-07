package com.nelioalves.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.CidadeEntity;
import com.nelioalves.cursomc.domain.dto.DTO_Cidade;
import com.nelioalves.cursomc.services.CidadeService;

@RestController
@RequestMapping(value="/estados/{param_EstadoId}/cidades")
public class REST_CidadeResource {

	@Autowired
	public CidadeService var_serviceCidade;
	
// ============================= METODO GET: faz uma busca get/find no BD por uma instância da entidade que já existe no BD ======================================= 
	@RequestMapping(method=RequestMethod.GET) // GET para TODAS AS Cidades, SEM paginação
	public ResponseEntity<List<DTO_Cidade>> metodoREST_findAllCidade(@PathVariable Integer param_EstadoId) {
		List<CidadeEntity> var_list = var_serviceCidade.metodoService_findAllCidade(param_EstadoId);
		List<DTO_Cidade> var_listDto = var_list.stream().map(obj -> new DTO_Cidade(obj)).collect(Collectors.toList());
		try {
		}
		catch (Exception e) {
			throw new Exception("ERRO_PADRAO#00??@Exception: "+e.getMessage())?;
		}
		return ResponseEntity.ok().body(var_listDto);
	}
}
