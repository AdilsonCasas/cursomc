package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.domainCategoria;
import com.nelioalves.cursomc.domain.domainCliente;
import com.nelioalves.cursomc.domain.dto.domainDTO_Categoria;
import com.nelioalves.cursomc.repositories.repositoryCategoria;
import com.nelioalves.cursomc.services.exception.service_exceptionGenericRuntimeException;

@Service
public class serviceCategoria {

	@Autowired
	private repositoryCategoria repo;
	
	public domainCategoria service_find(Integer Id) {
		Optional<domainCategoria> obj = repo.findById(Id);
		return obj.orElseThrow(() -> new service_exceptionGenericRuntimeException("Objeto não encontrado! Id: " + Id + ", Tipo: " + domainCategoria.class.getName()));
	}	
	
	public domainCategoria service_insert(domainCategoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public domainCategoria service_update(domainCategoria ObjAlterado) {
		domainCategoria ObjJaExistenteBD = service_find(ObjAlterado.getId());
		service_UpdateObjJaExistenteBD_from_ObjAlterado(ObjJaExistenteBD, ObjAlterado);
		return repo.save(ObjJaExistenteBD);
	}
	
	public void service_delete(Integer Id) {
		service_find(Id);
		//try {
			repo.deleteById(Id);
		//}
		//catch (DataIntegrityViolationException e) {
		//	throw new service_exceptionGenericRuntimeException("Não é possível Excluir uma Categoria que possui produtos associados");           
		//}
	}
	
	public List<domainCategoria> service_findAll() {
		return repo.findAll();
	}
	
	public Page<domainCategoria> service_findPage(Integer NumPage, Integer LinesPerPage, String orderBy, String directionOrderBy) {
		PageRequest service_pageRequest = PageRequest.of(NumPage, LinesPerPage, Direction.valueOf(directionOrderBy), orderBy);
		return repo.findAll(service_pageRequest);
	}
	
	public domainCategoria service_fromDTO_to_Categoria(domainDTO_Categoria objDTO) {
		return new domainCategoria(objDTO.getId(),objDTO.getNome());
	}
	
	private void service_UpdateObjJaExistenteBD_from_ObjAlterado(domainCategoria ObjJaExistenteBD, domainCategoria ObjAlterado) {
		ObjJaExistenteBD.setNome(ObjAlterado.getNome());
	}

}
