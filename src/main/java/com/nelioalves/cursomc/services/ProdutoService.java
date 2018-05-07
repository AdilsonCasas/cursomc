package com.nelioalves.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.CategoriaEntity;
import com.nelioalves.cursomc.domain.ProdutoEntity;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository var_repoProduto;

	@Autowired
	private CategoriaRepository var_repoCategoria;
	
	public ProdutoEntity metodoService_findProdutoById(Integer var_Id) throws Exception {
		ProdutoEntity var_obj = null;
		try {
			var_obj = var_repoProduto.metodoRepo_findProdutoById(var_Id);
		} catch (Exception e) {
			throw new Exception("ERRO_PADRAO#0058@Exception: "+e.getMessage());
		}
		if(var_obj == null) {
			throw new Exception("ERRO_PADRAO#0002@xiiiiiiii");
		}
		return var_obj;
	}	
	
	public Page<ProdutoEntity> metodoService_searchProduto(String var_nome, List<Integer> var_ids, Integer var_NumPage, Integer var_LinesPerPage, String var_orderBy, String var_directionOrderBy) {
		PageRequest var_service_pageRequest = PageRequest.of(var_NumPage, var_LinesPerPage, Direction.valueOf(var_directionOrderBy), var_orderBy);
		List<CategoriaEntity> var_list_cat = var_repoCategoria.findAllById(var_ids);
		return var_repoProduto.seach(var_nome, var_list_cat, var_service_pageRequest);
	}
	
}
