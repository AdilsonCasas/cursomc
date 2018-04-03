package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.domainCategoria;

@Repository
public interface repositoryCategoria extends JpaRepository<domainCategoria, Integer> {

}
