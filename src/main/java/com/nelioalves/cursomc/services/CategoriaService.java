package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.CategoriaDomain;
import com.nelioalves.cursomc.domain.dto.DTO_CategoriaDomain;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exception.Service_Exception_GenericRuntimeException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository var_repoCategoria;
	
	public CategoriaDomain metodoService_findCategoria(Integer var_Id) {
		Optional<CategoriaDomain> var_obj = var_repoCategoria.findById(var_Id);
		return var_obj.orElseThrow(() -> new Service_Exception_GenericRuntimeException("Objeto não encontrado! Id: " + var_Id + ", Tipo: " + CategoriaDomain.class.getName()));
	}	
	
	@Transactional
	public CategoriaDomain metodoService_insertCategoria(CategoriaDomain var_obj) {
		var_obj.setId(null);
		return var_repoCategoria.save(var_obj);
	}

	public CategoriaDomain metodoService_updateCategoria(CategoriaDomain var_ObjAlterado) {
		CategoriaDomain var_ObjJaExistenteBD = metodoService_findCategoria(var_ObjAlterado.getId());
		metodoService_UpdateObjJaExistenteBD_from_ObjAlterado(var_ObjJaExistenteBD, var_ObjAlterado);
		return var_repoCategoria.save(var_ObjJaExistenteBD);
	}
	
	public void metodoService_deleteCategoria(Integer var_Id) {
		metodoService_findCategoria(var_Id);
		//try {
			var_repoCategoria.deleteById(var_Id);
		//}
		//catch (DataIntegrityViolationException e) {
		//	throw new service_exceptionGenericRuntimeException("Não é possível Excluir uma Categoria que possui produtos associados");           
		//}
	}
	
	public List<CategoriaDomain> metodoService_findAllCategoria() {
		return var_repoCategoria.findAll();
	}
	
	public Page<CategoriaDomain> metodoService_findPageCategoria(Integer var_NumPage, Integer var_LinesPerPage, String var_orderBy, String var_directionOrderBy) {
		PageRequest var_service_pageRequest = PageRequest.of(var_NumPage, var_LinesPerPage, Direction.valueOf(var_directionOrderBy), var_orderBy);
		return var_repoCategoria.findAll(var_service_pageRequest);
	}
	
	public CategoriaDomain metodoService_fromDTO_to_Categoria(DTO_CategoriaDomain var_objDTO) {
		return new CategoriaDomain(var_objDTO.getId(),var_objDTO.getNome());
	}
	
	private void metodoService_UpdateObjJaExistenteBD_from_ObjAlterado(CategoriaDomain var_ObjJaExistenteBD, CategoriaDomain var_ObjAlterado) {
		var_ObjJaExistenteBD.setNome(var_ObjAlterado.getNome());
	}

}
