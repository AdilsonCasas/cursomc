package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.domainProduto;

@Repository
public interface ProdutoRepository extends JpaRepository<domainProduto, Integer> {

}
