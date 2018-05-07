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
	
	public List<CidadeEntity> metodoService_findAllCidade(Integer var_EstadoId) throws Exception {
		try {
			return var_repoCidade.metodoRepo_findCidades(var_EstadoId);
		} catch (Exception e) {
			throw new Exception("ERRO_PADRAO#0050@Exception: "+e.getMessage());
		}
	}

}
