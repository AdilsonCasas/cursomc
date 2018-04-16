package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {
	// a função 'findByEmail' abaixo, pelo simples fato de estar definida aqui dentro do 'repositoty' da entidade já será entendida pelo Framework e nem precisa
	// ter seu código escrito porque o Framework já identifica que o que se quer é fazer uma busca pelo atributo 'email' da classe.
	// mas o nome da função tem que seguir o padrão de nomenclatura, com 'find' minusculo e 'By' maiúsculo + nome do campo a ser pesquisado, sem traço nem underline.
	// 
	// No "site: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/"   item "4.3. Query methods" tem uma lista dos nomes de método padrão que vc 
	// pode usar e fazer o próprio Spring montar a consulta JPQL (SQL do JPA) pra vc. 
	ClienteEntity findByEmail(String email);
}
