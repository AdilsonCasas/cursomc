package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nelioalves.cursomc.domain.CategoriaEntity;
import com.nelioalves.cursomc.domain.dto.DTO_CategoriaEntity;
import com.nelioalves.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository var_repoCategoria;
	
	public CategoriaEntity metodoService_findCategoriaById(Integer var_Id) throws Exception {
		Optional<CategoriaEntity> var_obj = null;
		try {
			var_obj = var_repoCategoria.findById(var_Id);
		}
		catch (Exception e) {
			throw new Exception("ERRO_PADRAO#0001@Exception: "+e.getMessage());
		}
		return var_obj.get();
	}

	public List<CategoriaEntity> metodoService_findAllCategoria() {
		return var_repoCategoria.findAll();
	}
	
	public Page<CategoriaEntity> metodoService_findPageCategoria(Integer var_NumPage, Integer var_LinesPerPage, String var_orderBy, String var_directionOrderBy) {
		PageRequest var_service_pageRequest = PageRequest.of(var_NumPage, var_LinesPerPage, Direction.valueOf(var_directionOrderBy), var_orderBy);
		return var_repoCategoria.findAll(var_service_pageRequest);
	}
	
	@Transactional
	public CategoriaEntity metodoService_insertCategoria(CategoriaEntity var_obj) {
		var_obj.setId(null);
		return var_repoCategoria.save(var_obj);
	}

	public CategoriaEntity metodoService_updateCategoria(CategoriaEntity var_ObjAlterado) throws Exception {
/*
 * para alterar categoria
 *      {
          "nome" : "lalalal"
        }
 */
		CategoriaEntity var_ObjJaExistenteBD = metodoService_findCategoriaById(var_ObjAlterado.getId());
		metodoService_UpdateObjJaExistenteBD_from_ObjAlterado(var_ObjJaExistenteBD, var_ObjAlterado);
		return var_repoCategoria.save(var_ObjJaExistenteBD);
	}
	
	public void metodoService_deleteCategoria(Integer var_Id) throws Exception {
		metodoService_findCategoriaById(var_Id);
		var_repoCategoria.deleteById(var_Id);
	}
	
	public CategoriaEntity metodoService_fromDTO_to_Categoria(DTO_CategoriaEntity var_objDTO) {
		return new CategoriaEntity(var_objDTO.getId(),var_objDTO.getNome());
	}
	
	private void metodoService_UpdateObjJaExistenteBD_from_ObjAlterado(CategoriaEntity var_ObjJaExistenteBD, CategoriaEntity var_ObjAlterado) {
		var_ObjJaExistenteBD.setNome(var_ObjAlterado.getNome());
	}

}
