package com.nelioalves.cursomc.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.ItemPedidoDomain;
import com.nelioalves.cursomc.domain.PagamentoComBoletoDomain;
import com.nelioalves.cursomc.domain.PedidoDomain;
import com.nelioalves.cursomc.domain.enums.enumEstadoPagamento;
import com.nelioalves.cursomc.repositories.ItemPedidoRepository;
import com.nelioalves.cursomc.repositories.PagamentoRepository;
import com.nelioalves.cursomc.repositories.PedidoRepository;
import com.nelioalves.cursomc.services.exception.Service_Exception_GenericRuntimeException;
import com.nelioalves.cursomc.services.utils.Service_Utils_Boleto;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repoPedido;
	
	@Autowired
	private PagamentoRepository repoPagamento;
	
	@Autowired
	private Service_Utils_Boleto serviceUtils_Boleto;

	@Autowired
	private ProdutoService serviceProduto;

	@Autowired
	private ClienteService serviceCliente;

	@Autowired
	private ItemPedidoRepository repoItemPedido;

	@Autowired
	// a var 'serviceEmail' abaixo é instanciada no arq. de configuração 'ProfileTestConfig', sempre que uso um '@Autowired' há uma instanciação do componente do sistema citado.
	// como 'EmailService' é uma interface e não uma classe é necessário a instanciação por 'new'
	private EmailService serviceEmail;

	public PedidoDomain service_find(Integer Id) {
		Optional<PedidoDomain> obj = repoPedido.findById(Id);
		return obj.orElseThrow(() -> new Service_Exception_GenericRuntimeException("Objeto não encontrado! Id: " + Id + ", Tipo: " + PedidoDomain.class.getName()));
	}
	
	@Transactional
	public PedidoDomain service_insert(PedidoDomain obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(serviceCliente.service_find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(enumEstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoletoDomain) {
			PagamentoComBoletoDomain pgto = (PagamentoComBoletoDomain) obj.getPagamento();
			serviceUtils_Boleto.PreencherPagtoComBoleto(pgto, obj.getInstante());
		}
		repoPedido.save(obj);
		repoPagamento.save(obj.getPagamento());
		for (ItemPedidoDomain ip: obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(serviceProduto.service_find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		repoItemPedido.saveAll(obj.getItens());
		serviceEmail.sendOrderConfirmationHtmlEmail(obj);
		//serviceEmail.sendOrderConfirmationEmail(obj);
		// from=adilson.casas@gmail.com
		// to=maria@gmail.com; cc=; bcc=; 
		// sentDate=Mon Apr 09 21:54:50 BRT 2018; 
		// subject=Pedido confirmado! Código: 3; 
		// text=Pedido Número: 3, Instante: 09/04/2018 21:54:50, Cliente: Maria Silva, Situação do Pagamento: Pendente ...
		return obj;
	}
	
}
