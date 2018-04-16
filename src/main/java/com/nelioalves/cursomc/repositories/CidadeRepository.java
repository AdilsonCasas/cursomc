package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.CidadeEntity;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeEntity, Integer> {

}
