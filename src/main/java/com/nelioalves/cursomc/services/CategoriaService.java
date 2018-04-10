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
	private CategoriaRepository repoCategoria;
	
	public CategoriaDomain service_find(Integer Id) {
		Optional<CategoriaDomain> obj = repoCategoria.findById(Id);
		return obj.orElseThrow(() -> new Service_Exception_GenericRuntimeException("Objeto não encontrado! Id: " + Id + ", Tipo: " + CategoriaDomain.class.getName()));
	}	
	
	@Transactional
	public CategoriaDomain service_insert(CategoriaDomain obj) {
		obj.setId(null);
		return repoCategoria.save(obj);
	}
	
	public CategoriaDomain service_update(CategoriaDomain ObjAlterado) {
		CategoriaDomain ObjJaExistenteBD = service_find(ObjAlterado.getId());
		service_UpdateObjJaExistenteBD_from_ObjAlterado(ObjJaExistenteBD, ObjAlterado);
		return repoCategoria.save(ObjJaExistenteBD);
	}
	
	public void service_delete(Integer Id) {
		service_find(Id);
		//try {
			repoCategoria.deleteById(Id);
		//}
		//catch (DataIntegrityViolationException e) {
		//	throw new service_exceptionGenericRuntimeException("Não é possível Excluir uma Categoria que possui produtos associados");           
		//}
	}
	
	public List<CategoriaDomain> service_findAll() {
		return repoCategoria.findAll();
	}
	
	public Page<CategoriaDomain> service_findPage(Integer NumPage, Integer LinesPerPage, String orderBy, String directionOrderBy) {
		PageRequest service_pageRequest = PageRequest.of(NumPage, LinesPerPage, Direction.valueOf(directionOrderBy), orderBy);
		return repoCategoria.findAll(service_pageRequest);
	}
	
	public CategoriaDomain service_fromDTO_to_Categoria(DTO_CategoriaDomain objDTO) {
		return new CategoriaDomain(objDTO.getId(),objDTO.getNome());
	}
	
	private void service_UpdateObjJaExistenteBD_from_ObjAlterado(CategoriaDomain ObjJaExistenteBD, CategoriaDomain ObjAlterado) {
		ObjJaExistenteBD.setNome(ObjAlterado.getNome());
	}

}
