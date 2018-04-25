package com.nelioalves.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nelioalves.cursomc.domain.EstadoEntity;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoEntity, Integer> {

	@Transactional(readOnly=true)
	public List<EstadoEntity> findAllByOrderByNome();
}
