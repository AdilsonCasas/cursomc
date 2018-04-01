package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer Id) {
		Optional<Categoria> obj = repo.findById(Id);
//System.out.println("Id a buscar="+Id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + Id + ", Tipo: " + Categoria.class.getName()));
	}	
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
//System.out.println("obj.getNome="+obj.getNome());
		return repo.save(obj);
		//return repo.saveAll(Arrays.asList(obj));
	}
}
