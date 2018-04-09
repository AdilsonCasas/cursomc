package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.CidadeDomain;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeDomain, Integer> {

}
