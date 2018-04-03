package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.domainEndereco;

@Repository
public interface repositoryEndereco extends JpaRepository<domainEndereco, Integer> {

}
