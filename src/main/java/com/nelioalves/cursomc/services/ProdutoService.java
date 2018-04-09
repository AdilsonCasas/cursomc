package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.CategoriaDomain;
import com.nelioalves.cursomc.domain.ProdutoDomain;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;
import com.nelioalves.cursomc.services.exception.Service_Exception_GenericRuntimeException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repoProduto;

	@Autowired
	private CategoriaRepository repoCategoria;
	
	public ProdutoDomain service_find(Integer Id) {
		Optional<ProdutoDomain> obj = repoProduto.findById(Id);
		return obj.orElseThrow(() -> new Service_Exception_GenericRuntimeException("Objeto não encontrado! Id: " + Id + ", Tipo: " + ProdutoDomain.class.getName()));
	}	
	
	public Page<ProdutoDomain> service_search(String nome, List<Integer> ids, Integer NumPage, Integer LinesPerPage, String orderBy, String directionOrderBy) {
		PageRequest service_pageRequest = PageRequest.of(NumPage, LinesPerPage, Direction.valueOf(directionOrderBy), orderBy);
		List<CategoriaDomain> list_cat = repoCategoria.findAllById(ids);
		return repoProduto.seach(nome, list_cat, service_pageRequest);
	}
	
}
