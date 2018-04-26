package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.CategoriaEntity;
import com.nelioalves.cursomc.domain.ProdutoEntity;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;
import com.nelioalves.cursomc.services.exception.Service_Exception_GenericRuntimeException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository var_repoProduto;

	@Autowired
	private CategoriaRepository var_repoCategoria;
	
	public ProdutoEntity metodoService_findProduto(Integer var_Id) {
		Optional<ProdutoEntity> var_obj = var_repoProduto.findById(var_Id);
		return var_obj.orElseThrow(() -> new Service_Exception_GenericRuntimeException("Produto não encontrado! Id: " + var_Id + ", Tipo: " + ProdutoEntity.class.getName()));
	}	
	
	public Page<ProdutoEntity> metodoService_searchProduto(String var_nome, List<Integer> var_ids, Integer var_NumPage, Integer var_LinesPerPage, String var_orderBy, String var_directionOrderBy) {
		PageRequest var_service_pageRequest = PageRequest.of(var_NumPage, var_LinesPerPage, Direction.valueOf(var_directionOrderBy), var_orderBy);
		List<CategoriaEntity> var_list_cat = var_repoCategoria.findAllById(var_ids);
		return var_repoProduto.seach(var_nome, var_list_cat, var_service_pageRequest);
	}
	
}
