package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.domainCategoria;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public domainCategoria service_find(Integer Id) {
		Optional<domainCategoria> obj = repo.findById(Id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + Id + ", Tipo: " + domainCategoria.class.getName()));
	}	
	
	public domainCategoria service_insert(domainCategoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public domainCategoria service_update(domainCategoria obj) {
		service_find(obj.getId());
		return repo.save(obj);
	}
	
	public void service_delete(Integer Id) {
		service_find(Id);
		try {
			repo.deleteById(Id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível Excluir uma Categoria que possui produtos associados");           
		}
	}
	
	public List<domainCategoria> service_findAll() {
		return repo.findAll();
	}
	
}
