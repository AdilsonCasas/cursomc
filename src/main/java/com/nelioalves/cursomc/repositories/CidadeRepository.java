package com.nelioalves.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nelioalves.cursomc.domain.CidadeEntity;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeEntity, Integer> {

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM CidadeEntity obj WHERE obj.estado.Id = :param_EstadoId ORDER BY obj.nome")
	public List<CidadeEntity> metodoRepo_findCidades(@Param("param_EstadoId") Integer var_EstadoId);
}
