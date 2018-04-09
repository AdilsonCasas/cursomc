package com.nelioalves.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.CategoriaDomain;
import com.nelioalves.cursomc.domain.ProdutoDomain;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoDomain, Integer> {
	// o nome desta função não é o mesmo caso descrito em "repositoryCliente" para a função 'findByEmail', ou seja, ela não terá a imprementação automatizada pelo
	// Spring. Por isso é necessário colocar a instrução JPQL dentro da diretiva @Query abaixo. Observe que existem duas variáveis ':nome' e ':categorias'
	// dentro do @Query, que deve ser especificado com a diretiva '@Param'.
	// No JPQL deve-se sempre usar o nome oficial da Classe, como ela é definida aqui no sistema.
	@Query("SELECT DISTINCT obj FROM ProdutoDomain obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	Page<ProdutoDomain>	seach(@Param("nome") String nome, @Param("categorias") List<CategoriaDomain> list_cat, Pageable pageRequest);
	
	// Uma outra forma que também funcionaria (de forma similar ao repositoryCliente, com um nome de função padrão seria assim:
	//Page<ProdutoDomain>	findDistinctByNomeContainingAndCategoriasIn(String nome, List<CategoriaDomain> categorias, Pageable pageRequest);
}