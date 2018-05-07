package com.nelioalves.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc.domain.ClienteEntity;
import com.nelioalves.cursomc.domain.dto.DTO_ClienteEntity_Completo;
import com.nelioalves.cursomc.domain.dto.DTO_ClienteEntity_nome_email;
import com.nelioalves.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class REST_ClienteResource {

	@Autowired
	public ClienteService var_serviceCliente;
	
// ============================= METODO GET: faz uma busca get/find no BD por uma instância da entidade que já existe no BD ======================================= 
	@RequestMapping(value="/{param_Id}", method=RequestMethod.GET) // GET para SOMENTE UM Cliente (busca por Id)
	public ResponseEntity<ClienteEntity> metodoREST_findClienteById(@PathVariable Integer param_Id) throws Exception {
		try {
			ClienteEntity var_obj = var_serviceCliente.metodoService_findClienteById(param_Id);
		}
		catch (Exception e) {
			throw new Exception("ERRO_PADRAO#00??@Exception: "+e.getMessage())?;
		}
		return ResponseEntity.ok().body(var_obj);
	}
	
	@RequestMapping(value="/email", method=RequestMethod.GET) // GET para SOMENTE UM Cliente (busca por email)
	// a chamada no "postman" deve ser GET para o end : 'localhost:8080/clientes/email?param_email=pp890645@gmail.com'
	public ResponseEntity<ClienteEntity> metodoREST_findClienteByEmail(@RequestParam(value="param_email") String param_Email) throws Exception {
		try {
			ClienteEntity var_obj = var_serviceCliente.metodoService_findClienteByEmail(param_Email);
		}
		catch (Exception e) {
			throw new Exception("ERRO_PADRAO#00??@Exception: "+e.getMessage())?;
		}
		return ResponseEntity.ok().body(var_obj);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.GET) // Get para TODOS os Clientes, SEM paginação
	public ResponseEntity<List<DTO_ClienteEntity_nome_email>> metodoREST_findAllCliente() {
		List<ClienteEntity> var_list = var_serviceCliente.metodoService_findAllCliente();
		List<DTO_ClienteEntity_nome_email> var_listDto = var_list.stream().map(var_obj -> new DTO_ClienteEntity_nome_email(var_obj)).collect(Collectors.toList());
		try {
		}
		catch (Exception e) {
			throw new Exception("ERRO_PADRAO#00??@Exception: "+e.getMessage())?;
		}
		return ResponseEntity.ok().body(var_listDto);
	}
		
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/page", method=RequestMethod.GET) // Get para TODOS os Clientes, COM paginação
	public ResponseEntity<Page<DTO_ClienteEntity_nome_email>> metodoREST_findPageCliente(
					@RequestParam(value="NumPage", defaultValue="0") Integer var_NumPage, 
					@RequestParam(value="LinesPerPage", defaultValue="24") Integer var_LinesPerPage, 
					@RequestParam(value="orderBy" , defaultValue="nome") String var_orderBy, 
					@RequestParam(value="directionOrderBy", defaultValue="ASC") String var_directionOrderBy) {
		// os parâmetros do método 'resource_findPage' virão de parâmetros colocados na chamada do recurso na url do app chamador
		// ex1 de chamada: "http://localhost:8080/clientes/page?NumPage=0&LinesPerPage=2&orderBy=nome&directionOrderBy=DESC" (NumPage=0, significa primeira página)
		// ex2 de chamada: "http://localhost:8080/clientes/page" (sem parâmetros)
		// ex3 de chamada: "http://localhost:8080/clientes/page?LinesPerPage=2" (somente parâmetro 'LinesPerPage' informado, o resto pega o default)
		Page<ClienteEntity> var_list = var_serviceCliente.metodoService_findPageCliente(var_NumPage, var_LinesPerPage, var_orderBy, var_directionOrderBy);
		Page<DTO_ClienteEntity_nome_email> var_listDto = var_list.map(var_obj -> new DTO_ClienteEntity_nome_email(var_obj));
		try {
		}
		catch (Exception e) {
			throw new Exception("ERRO_PADRAO#00??@Exception: "+e.getMessage())?;
		}
		return ResponseEntity.ok().body(var_listDto);
	}
		
// ============================= METODO POST: faz um "insert" de nova instância da entidade no BD ================================================================= 
	@RequestMapping(method=RequestMethod.POST)
	// a diretiva '@Valid' abaixo percebe/captura o resultado do método 'isValid' definido na classe 'serviceClienteInsertValidator'
	public ResponseEntity<Void> metodoREST_insertCliente(@Valid @RequestBody DTO_ClienteEntity_Completo var_objDTO) {
		ClienteEntity var_obj = var_serviceCliente.metodoService_fromDTO_to_Cliente(var_objDTO);
		var_obj = var_serviceCliente.metodoService_insertCliente(var_obj);
		URI var_uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(var_obj.getId()).toUri();
		try {
		}
		catch (Exception e) {
			throw new Exception("ERRO_PADRAO#00??@Exception: "+e.getMessage())?;
		}
		return ResponseEntity.created(var_uri).build();
	}
	
// ============================= METODO PUT: faz um "update" no BD em uma instância da entidade que já existe no BD ============================================== 
	@RequestMapping(value="/{param_Id}", method=RequestMethod.PUT)
	// o "@Valid" abaixo é parte do "Bean Validate" que faz parte od Java EE, ele chama a validação definida nas diretivas incluídas no "domain" do cliente
	public ResponseEntity<Void> metodoREST_updateCliente(@Valid @RequestBody DTO_ClienteEntity_nome_email var_objDTO, @PathVariable Integer param_Id) throws Exception {
		ClienteEntity var_obj = var_serviceCliente.metodoService_fromDTO_to_Cliente(var_objDTO);
		var_obj.setId(param_Id);
		var_obj = var_serviceCliente.metodoService_updateCliente(var_obj);
		try {
		}
		catch (Exception e) {
			throw new Exception("ERRO_PADRAO#00??@Exception: "+e.getMessage())?;
		}
		return ResponseEntity.noContent().build();
	}
	
// ============================= METODO DELETE: faz um "delete" no BD em uma instância da entidade que já existe no BD ======================================= 
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{param_Id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> metodoREST_deleteCliente(@PathVariable Integer param_Id) throws Exception {
		var_serviceCliente.metodoService_deleteCliente(param_Id);
		try {
		}
		catch (Exception e) {
			throw new Exception("ERRO_PADRAO#00??@Exception: "+e.getMessage())?;
		}
		return ResponseEntity.noContent().build();
	}
// ============================= o METODO POST abaixo processa o endpoint "/clientes/picture que será para enviar arq ao Amazon S3 =========================== 
	@RequestMapping(value="/picture", method=RequestMethod.POST)
	public ResponseEntity<Void> metodoREST_uploadProfilePictureToAmazonS3(@RequestParam(name="file") MultipartFile var_multipartFile) throws Exception {
		URI var_uri = var_serviceCliente.metodoService_uploadProfilePictureService(var_multipartFile);
		try {
		}
		catch (Exception e) {
			throw new Exception("ERRO_PADRAO#00??@Exception: "+e.getMessage())?;
		}
		return ResponseEntity.created(var_uri).build();
	}
}
