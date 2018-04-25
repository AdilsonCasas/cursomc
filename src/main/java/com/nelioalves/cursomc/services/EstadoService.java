package com.nelioalves.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.EstadoEntity;
import com.nelioalves.cursomc.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository var_repoEstado;
	
	public List<EstadoEntity> metodoService_findAllEstado() {
		return var_repoEstado.findAllByOrderByNome();
	}

}
