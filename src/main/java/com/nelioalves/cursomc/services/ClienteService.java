package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.CidadeEntity;
import com.nelioalves.cursomc.domain.ClienteEntity;
import com.nelioalves.cursomc.domain.EnderecoEntity;
import com.nelioalves.cursomc.domain.dto.DTO_ClienteEntity_Completo;
import com.nelioalves.cursomc.domain.dto.DTO_ClienteEntity_nome_email;
import com.nelioalves.cursomc.domain.enums.enumPerfilUsuario;
import com.nelioalves.cursomc.domain.enums.enumTipoCliente;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.security.UserSpringSecurity;
import com.nelioalves.cursomc.services.exception.Service_Exception_GenericRuntimeException;

@Service
public class ClienteService {

	@Autowired
	private BCryptPasswordEncoder var_bCryptPasswordEncoder;

	@Autowired
	private ClienteRepository var_repoCliente;
	
	@Autowired
	private CidadeRepository var_repoCidade;
	
	@Autowired
	private EnderecoRepository var_repoEndereco;
	
	public ClienteEntity metodoService_findCliente(Integer var_Id) {
		UserSpringSecurity var_user = UserService.metodoService_authenticaded();
		if(var_user == null || !var_user.metodoUserSpringSecurity_hasRole(enumPerfilUsuario.ADMIN) && !var_Id.equals(var_user.getId())) {
			throw new Service_Exception_GenericRuntimeException("Acesso Negado!" + var_Id + ", Tipo: " + ClienteEntity.class.getName());
		}
		Optional<ClienteEntity> var_obj = var_repoCliente.findById(var_Id);
		return var_obj.orElseThrow(() -> new Service_Exception_GenericRuntimeException("Objeto não encontrado! Id: " + var_Id + ", Tipo: " + ClienteEntity.class.getName()));
	}	
	
	// a diretiva "Transational" faz uma operação integrando duas ou mais entidades no BD, no caso de Clientes há relações com a entidade Endereços e Cidade/Estado
	@Transactional
	public ClienteEntity metodoService_insertCliente(ClienteEntity var_obj) {
		var_obj.setId(null);
		var_obj = var_repoCliente.save(var_obj);
		var_repoEndereco.saveAll(var_obj.getEnderecos());
		return var_obj;
	}
	
	public ClienteEntity metodoService_updateCliente(ClienteEntity var_ObjAlterado) {
		ClienteEntity var_ObjJaExistenteBD = metodoService_findCliente(var_ObjAlterado.getId());
		metodoService_UpdateObjJaExistenteBD_from_ObjAlterado(var_ObjJaExistenteBD, var_ObjAlterado);
		return var_repoCliente.save(var_ObjJaExistenteBD);
	}
	
	public void metodoService_deleteCliente(Integer var_Id) {
		metodoService_findCliente(var_Id);
		//try {
			var_repoCliente.deleteById(var_Id);
		//}
		//catch (DataIntegrityViolationException e) {
		//	throw new service_exceptionGenericRuntimeException("kskdj lkj asldfj lkdkfj lasdj flskjf lksjf lksjf lasjflkdjf lkdjf");           
		//}
	}
	
	public List<ClienteEntity> metodoService_findAllCliente() {
		return var_repoCliente.findAll();
	}
	
	public Page<ClienteEntity> metodoService_findPageCliente(Integer var_NumPage, Integer var_LinesPerPage, String var_orderBy, String var_directionOrderBy) {
		PageRequest var_service_pageRequest = PageRequest.of(var_NumPage, var_LinesPerPage, Direction.valueOf(var_directionOrderBy), var_orderBy);
		return var_repoCliente.findAll(var_service_pageRequest);
	}
	
	public ClienteEntity metodoService_fromDTO_to_Cliente(DTO_ClienteEntity_nome_email var_objDTO) {
		return new ClienteEntity(var_objDTO.getId(), var_objDTO.getNome(), var_objDTO.getEmail(), null, null, null);
	}
	
	public ClienteEntity metodoService_fromDTO_to_Cliente(DTO_ClienteEntity_Completo var_objDTO) {
		ClienteEntity var_cli = new ClienteEntity(null, var_objDTO.getNome(), var_objDTO.getEmail(), var_objDTO.getCpfOuCnpj(), enumTipoCliente.toEnum(var_objDTO.getTipoCliente()), var_bCryptPasswordEncoder.encode(var_objDTO.getSenha()));
		CidadeEntity var_cid = var_repoCidade.getOne(var_objDTO.getCidadeId());
		EnderecoEntity var_end = new EnderecoEntity(null, var_objDTO.getLogradouro(), var_objDTO.getNumero(), var_objDTO.getComplemento(), var_objDTO.getBairro(), var_objDTO.getCep(), var_cli, var_cid);
		var_cli.getEnderecos().add(var_end);
		var_cli.getTelefones().add(var_objDTO.getTelefone1());
		if(var_objDTO.getTelefone2()!=null) {
			var_cli.getTelefones().add(var_objDTO.getTelefone2());
		}
		if(var_objDTO.getTelefone3()!=null) {
			var_cli.getTelefones().add(var_objDTO.getTelefone3());
		}
		return var_cli;
	}
	
	private void metodoService_UpdateObjJaExistenteBD_from_ObjAlterado(ClienteEntity var_ObjJaExistenteBD, ClienteEntity var_ObjAlterado) {
		var_ObjJaExistenteBD.setNome(var_ObjAlterado.getNome());
		var_ObjJaExistenteBD.setEmail(var_ObjAlterado.getEmail());
	}

}
