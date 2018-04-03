package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.domainEstado;

@Repository
public interface repositoryEstado extends JpaRepository<domainEstado, Integer> {

}
