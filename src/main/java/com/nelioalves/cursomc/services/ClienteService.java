package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.CidadeDomain;
import com.nelioalves.cursomc.domain.ClienteDomain;
import com.nelioalves.cursomc.domain.EnderecoDomain;
import com.nelioalves.cursomc.domain.dto.DTO_ClienteDomain_Completo;
import com.nelioalves.cursomc.domain.dto.DTO_ClienteDomain_nome_email;
import com.nelioalves.cursomc.domain.enums.enumTipoCliente;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.services.exception.Service_Exception_GenericRuntimeException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repoCliente;
	
	@Autowired
	private CidadeRepository repoCidade;
	
	@Autowired
	private EnderecoRepository repoEndereco;
	
	public ClienteDomain service_find(Integer Id) {
		Optional<ClienteDomain> obj = repoCliente.findById(Id);
		return obj.orElseThrow(() -> new Service_Exception_GenericRuntimeException("Objeto não encontrado! Id: " + Id + ", Tipo: " + ClienteDomain.class.getName()));
	}	
	
	// a diretiva "Transational" faz uma operação integrando duas ou mais entidades no BD, no caso de Clientes há relações com a entidade Endereços e Cidade/Estado
	@Transactional
	public ClienteDomain service_insert(ClienteDomain obj) {
		obj.setId(null);
		obj = repoCliente.save(obj);
		repoEndereco.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public ClienteDomain service_update(ClienteDomain ObjAlterado) {
		ClienteDomain ObjJaExistenteBD = service_find(ObjAlterado.getId());
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
	
	public List<ClienteDomain> service_findAll() {
		return repoCliente.findAll();
	}
	
	public Page<ClienteDomain> service_findPage(Integer NumPage, Integer LinesPerPage, String orderBy, String directionOrderBy) {
		PageRequest service_pageRequest = PageRequest.of(NumPage, LinesPerPage, Direction.valueOf(directionOrderBy), orderBy);
		return repoCliente.findAll(service_pageRequest);
	}
	
	public ClienteDomain service_fromDTO_to_Cliente(DTO_ClienteDomain_nome_email objDTO) {
		return new ClienteDomain(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	
	public ClienteDomain service_fromDTO_to_Cliente(DTO_ClienteDomain_Completo objDTO) {
		ClienteDomain cli = new ClienteDomain(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), enumTipoCliente.toEnum(objDTO.getTipoCliente()));
		CidadeDomain cid = repoCidade.getOne(objDTO.getCidadeId());
		EnderecoDomain end = new EnderecoDomain(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
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
	
	private void service_UpdateObjJaExistenteBD_from_ObjAlterado(ClienteDomain ObjJaExistenteBD, ClienteDomain ObjAlterado) {
		ObjJaExistenteBD.setNome(ObjAlterado.getNome());
		ObjJaExistenteBD.setEmail(ObjAlterado.getEmail());
	}

}
