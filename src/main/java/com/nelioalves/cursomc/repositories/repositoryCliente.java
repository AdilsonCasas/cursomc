package com.nelioalves.cursomc.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.domainCliente;

@Repository
public interface repositoryCliente extends JpaRepository<domainCliente, Integer> {
	// a função 'findByEmail' abaixo, pelo simples fato de estar definida aqui dentro do 'repositoty' da entidade já será entendida pelo Framework e nem precisa
	// ter seu código escrito porque o Framework já identifica que o que se quer é fazer uma busca pelo atributo 'email' da classe.
	// mas o nome da função tem que seguir o padrão de nomenclatura, com 'find' minusculo e 'By' maiúsculo + nome do campo a ser pesquisado, sem traço nem underline.
	@Transactional
	domainCliente findByEmail(String email);
}
