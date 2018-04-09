package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.ItemPedidoDomain;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedidoDomain, Integer> {

}
