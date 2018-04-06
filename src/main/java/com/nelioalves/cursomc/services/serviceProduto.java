package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.domainCategoria;
import com.nelioalves.cursomc.domain.domainProduto;
import com.nelioalves.cursomc.repositories.repositoryCategoria;
import com.nelioalves.cursomc.repositories.repositoryProduto;
import com.nelioalves.cursomc.services.exception.service_exceptionGenericRuntimeException;

@Service
public class serviceProduto {

	@Autowired
	private repositoryProduto repoProduto;

	@Autowired
	private repositoryCategoria repoCategoria;
	
	public domainProduto service_find(Integer Id) {
		Optional<domainProduto> obj = repoProduto.findById(Id);
		return obj.orElseThrow(() -> new service_exceptionGenericRuntimeException("Objeto não encontrado! Id: " + Id + ", Tipo: " + domainProduto.class.getName()));
	}	
	
	public Page<domainProduto> service_search(String nome, List<Integer> ids, Integer NumPage, Integer LinesPerPage, String orderBy, String directionOrderBy) {
		PageRequest service_pageRequest = PageRequest.of(NumPage, LinesPerPage, Direction.valueOf(directionOrderBy), orderBy);
		List<domainCategoria> list_cat = repoCategoria.findAllById(ids);
		return repoProduto.seach(nome, list_cat, service_pageRequest);
	}
	
}
