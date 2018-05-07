package com.nelioalves.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nelioalves.cursomc.domain.CategoriaEntity;
import com.nelioalves.cursomc.domain.ProdutoEntity;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {

	// o nome desta função não é o mesmo caso descrito em "repositoryCliente" para a função 'findByEmail', ou seja, ela não terá a imprementação automatizada pelo
	// Spring. Por isso é necessário colocar a instrução JPQL dentro da diretiva @Query abaixo. Observe que existem duas variáveis ':nome' e ':categorias'
	// dentro do @Query, que deve ser especificado com a diretiva '@Param'.
	// No JPQL deve-se sempre usar o nome oficial da Classe, como ela é definida aqui no sistema.
	@Query("SELECT DISTINCT obj FROM ProdutoEntity obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:param_nome% AND cat IN :param_categorias")
	Page<ProdutoEntity>	seach(@Param("param_nome") String var_nome, @Param("param_categorias") List<CategoriaEntity> list_cat, Pageable pageRequest);

	// Uma outra forma que também funcionaria (de forma similar ao repositoryCliente, com um nome de função padrão seria assim:
	//Page<ProdutoEntity>	findDistinctByNomeContainingAndCategoriasIn(String nome, List<CategoriaEntity> categorias, Pageable pageRequest);

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM ProdutoEntity obj WHERE obj.Id = :param_Id")
	public ProdutoEntity metodoRepo_findProdutoById(@Param("param_Id") Integer var_Id);
}
