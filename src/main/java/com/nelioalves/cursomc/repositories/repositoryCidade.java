package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.domainCidade;

@Repository
public interface repositoryCidade extends JpaRepository<domainCidade, Integer> {

}
