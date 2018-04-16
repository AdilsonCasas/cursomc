package com.nelioalves.cursomc.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.ItemPedidoEntity;
import com.nelioalves.cursomc.domain.PagamentoComBoletoEntity;
import com.nelioalves.cursomc.domain.PedidoEntity;
import com.nelioalves.cursomc.domain.enums.enumEstadoPagamento;
import com.nelioalves.cursomc.repositories.ItemPedidoRepository;
import com.nelioalves.cursomc.repositories.PagamentoRepository;
import com.nelioalves.cursomc.repositories.PedidoRepository;
import com.nelioalves.cursomc.services.exception.Service_Exception_GenericRuntimeException;
import com.nelioalves.cursomc.services.utils.Service_Utils_Boleto;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository var_repoPedido;
	
	@Autowired
	private PagamentoRepository var_repoPagamento;
	
	@Autowired
	private Service_Utils_Boleto var_serviceUtils_Boleto;

	@Autowired
	private ProdutoService var_serviceProduto;

	@Autowired
	private ClienteService var_serviceCliente;

	@Autowired
	private ItemPedidoRepository var_repoItemPedido;

	@Autowired
	// a var 'serviceEmail' abaixo é instanciada no arq. de configuração 'ProfileTestConfig', sempre que uso um '@Autowired' há uma instanciação do componente do sistema citado.
	// como 'EmailService' é uma interface e não uma classe é necessário a instanciação por 'new'
	private EmailService var_serviceEmail;

	public PedidoEntity metodoService_findPedido(Integer var_Id) {
		Optional<PedidoEntity> var_obj = var_repoPedido.findById(var_Id);
		return var_obj.orElseThrow(() -> new Service_Exception_GenericRuntimeException("Objeto não encontrado! Id: " + var_Id + ", Tipo: " + PedidoEntity.class.getName()));
	}
	
	@Transactional
	public PedidoEntity metodoService_insertPedido(PedidoEntity var_obj) {
/*
 * Para testar a inserção de novo pedido use o seguinte JSON:
 * 
 {
	"cliente" : {"id" : 1},
	"enderecoDeEntrega" : {"id" : 1},
	"pagamento" : {
		"numeroDeParcelas" : 10,
		"@type": "pagamentoComCartao"
	},
	"itens" : [
		{
		"quantidade" : 2,
		"produto" : {"id" : 3}
		},
		{
		"quantidade" : 1,
		"produto" : {"id" : 1}
		}
	]
}
 */
		var_obj.setId(null);
		var_obj.setInstante(new Date());
		var_obj.setCliente(var_serviceCliente.metodoService_findCliente(var_obj.getCliente().getId()));
		var_obj.getPagamento().setEstado(enumEstadoPagamento.PENDENTE);
		var_obj.getPagamento().setPedido(var_obj);
		if(var_obj.getPagamento() instanceof PagamentoComBoletoEntity) {
			PagamentoComBoletoEntity var_pgto = (PagamentoComBoletoEntity) var_obj.getPagamento();
			var_serviceUtils_Boleto.metodoService_utils_PreencherPagtoComBoleto(var_pgto, var_obj.getInstante());
		}
		var_repoPedido.save(var_obj);
		var_repoPagamento.save(var_obj.getPagamento());
		for (ItemPedidoEntity var_ip: var_obj.getItens()) {
			var_ip.setDesconto(0.0);
			var_ip.setProduto(var_serviceProduto.metodoService_findProduto(var_ip.getProduto().getId()));
			var_ip.setPreco(var_ip.getProduto().getPreco());
			var_ip.setPedido(var_obj);
		}
		var_repoItemPedido.saveAll(var_obj.getItens());
		//var_serviceEmail.sendOrderConfirmationHtmlEmail(obj);
		//var_serviceEmail.sendOrderConfirmationEmail(obj);
		// from=adilson.casas@gmail.com
		// to=maria@gmail.com; cc=; bcc=; 
		// sentDate=Mon Apr 09 21:54:50 BRT 2018; 
		// subject=Pedido confirmado! Código: 3; 
		// text=Pedido Número: 3, Instante: 09/04/2018 21:54:50, Cliente: Maria Silva, Situação do Pagamento: Pendente ...
		return var_obj;
	}
	
}
