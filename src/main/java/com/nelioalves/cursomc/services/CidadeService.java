package com.nelioalves.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.CidadeEntity;
import com.nelioalves.cursomc.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository var_repoCidade;
	
	public List<CidadeEntity> metodoService_findAllCidade(Integer var_EstadoId) {
		return var_repoCidade.metodoRepo_findCidades(var_EstadoId);
	}

}
