package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nelioalves.cursomc.domain.CategoriaEntity;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Integer> {

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM CategoriaEntity obj WHERE obj.Id = :param_Id")
	public CategoriaEntity metodoRepo_findCategoriaById(@Param("param_Id") Integer var_Id);
}
