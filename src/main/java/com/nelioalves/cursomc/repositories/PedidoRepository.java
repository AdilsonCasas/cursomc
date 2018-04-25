package com.nelioalves.cursomc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nelioalves.cursomc.domain.ClienteEntity;
import com.nelioalves.cursomc.domain.PedidoEntity;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> {
	
	@Transactional(readOnly=true)
	Page <PedidoEntity> findByCliente(ClienteEntity obj_Cliente, Pageable pageRequest);

}
