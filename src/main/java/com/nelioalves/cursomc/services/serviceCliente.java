package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.domainCliente;
import com.nelioalves.cursomc.domain.dto.domainDTO_Cliente;
import com.nelioalves.cursomc.repositories.repositoryCliente;
import com.nelioalves.cursomc.services.exception.service_exceptionGenericRuntimeException;

@Service
public class serviceCliente {

	@Autowired
	private repositoryCliente repo;
	
	public domainCliente service_find(Integer Id) {
		Optional<domainCliente> obj = repo.findById(Id);
		return obj.orElseThrow(() -> new service_exceptionGenericRuntimeException("Objeto não encontrado! Id: " + Id + ", Tipo: " + domainCliente.class.getName()));
	}	
	
	public domainCliente service_insert(domainCliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public domainCliente service_update(domainCliente ObjAlterado) {
		domainCliente ObjJaExistenteBD = service_find(ObjAlterado.getId());
		service_UpdateObjJaExistenteBD_from_ObjAlterado(ObjJaExistenteBD, ObjAlterado);
		return repo.save(ObjJaExistenteBD);
	}
	
	public void service_delete(Integer Id) {
		service_find(Id);
		//try {
			repo.deleteById(Id);
		//}
		//catch (DataIntegrityViolationException e) {
		//	throw new service_exceptionGenericRuntimeException("Não é possível Excluir uma Cliente que possui produtos associados");           
		//}
	}
	
	public List<domainCliente> service_findAll() {
		return repo.findAll();
	}
	
	public Page<domainCliente> service_findPage(Integer NumPage, Integer LinesPerPage, String orderBy, String directionOrderBy) {
		PageRequest service_pageRequest = PageRequest.of(NumPage, LinesPerPage, Direction.valueOf(directionOrderBy), orderBy);
		return repo.findAll(service_pageRequest);
	}
	
	public domainCliente service_fromDTO_to_Cliente(domainDTO_Cliente objDTO) {
		return new domainCliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	
	private void service_UpdateObjJaExistenteBD_from_ObjAlterado(domainCliente ObjJaExistenteBD, domainCliente ObjAlterado) {
		ObjJaExistenteBD.setNome(ObjAlterado.getNome());
		ObjJaExistenteBD.setEmail(ObjAlterado.getEmail());
	}

}
