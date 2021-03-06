package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.ItemPedidoEntity;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedidoEntity, Integer> {

}
