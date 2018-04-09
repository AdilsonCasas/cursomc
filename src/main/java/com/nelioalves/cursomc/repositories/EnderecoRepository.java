package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.EnderecoDomain;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoDomain, Integer> {

}
