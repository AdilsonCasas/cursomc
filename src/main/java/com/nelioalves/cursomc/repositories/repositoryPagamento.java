package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.domainPagamento;

@Repository
public interface repositoryPagamento extends JpaRepository<domainPagamento, Integer> {

}
