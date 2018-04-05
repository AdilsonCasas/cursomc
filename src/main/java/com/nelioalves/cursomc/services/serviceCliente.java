package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.domainCidade;
import com.nelioalves.cursomc.domain.domainCliente;
import com.nelioalves.cursomc.domain.domainEndereco;
import com.nelioalves.cursomc.domain.dto.domainDTO_Cliente_Completo;
import com.nelioalves.cursomc.domain.dto.domainDTO_Cliente_nome_email;
import com.nelioalves.cursomc.domain.enums.enumTipoCliente;
import com.nelioalves.cursomc.repositories.repositoryCidade;
import com.nelioalves.cursomc.repositories.repositoryCliente;
import com.nelioalves.cursomc.repositories.repositoryEndereco;
import com.nelioalves.cursomc.services.exception.service_exceptionGenericRuntimeException;

@Service
public class serviceCliente {

	@Autowired
	private repositoryCliente repoCliente;
	
	@Autowired
	private repositoryCidade repoCidade;
	
	@Autowired
	private repositoryEndereco repoEndereco;
	
	public domainCliente service_find(Integer Id) {
		Optional<domainCliente> obj = repoCliente.findById(Id);
		return obj.orElseThrow(() -> new service_exceptionGenericRuntimeException("Objeto não encontrado! Id: " + Id + ", Tipo: " + domainCliente.class.getName()));
	}	
	
	// a diretiva "Transational" faz uma operação integrando duas ou mais entidades no BD, no caso de Clientes há relações com a entidade Endereços e Cidade/Estado
	@Transactional
	public domainCliente service_insert(domainCliente obj) {
		obj.setId(null);
		obj = repoCliente.save(obj);
		repoEndereco.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public domainCliente service_update(domainCliente ObjAlterado) {
		domainCliente ObjJaExistenteBD = service_find(ObjAlterado.getId());
		service_UpdateObjJaExistenteBD_from_ObjAlterado(ObjJaExistenteBD, ObjAlterado);
		return repoCliente.save(ObjJaExistenteBD);
	}
	
	public void service_delete(Integer Id) {
		service_find(Id);
		//try {
			repoCliente.deleteById(Id);
		//}
		//catch (DataIntegrityViolationException e) {
		//	throw new service_exceptionGenericRuntimeException("kskdj lkj asldfj lkdkfj lasdj flskjf lksjf lksjf lasjflkdjf lkdjf");           
		//}
	}
	
	public List<domainCliente> service_findAll() {
		return repoCliente.findAll();
	}
	
	public Page<domainCliente> service_findPage(Integer NumPage, Integer LinesPerPage, String orderBy, String directionOrderBy) {
		PageRequest service_pageRequest = PageRequest.of(NumPage, LinesPerPage, Direction.valueOf(directionOrderBy), orderBy);
		return repoCliente.findAll(service_pageRequest);
	}
	
	public domainCliente service_fromDTO_to_Cliente(domainDTO_Cliente_nome_email objDTO) {
		return new domainCliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	
	public domainCliente service_fromDTO_to_Cliente(domainDTO_Cliente_Completo objDTO) {
		domainCliente cli = new domainCliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), enumTipoCliente.toEnum(objDTO.getTipoCliente()));
		domainCidade cid = repoCidade.getOne(objDTO.getCidadeId());
		domainEndereco end = new domainEndereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		if(objDTO.getTelefone2()!=null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		if(objDTO.getTelefone3()!=null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		return cli;
	}
	
	private void service_UpdateObjJaExistenteBD_from_ObjAlterado(domainCliente ObjJaExistenteBD, domainCliente ObjAlterado) {
		ObjJaExistenteBD.setNome(ObjAlterado.getNome());
		ObjJaExistenteBD.setEmail(ObjAlterado.getEmail());
	}

}
