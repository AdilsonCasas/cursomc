package com.nelioalves.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.domainItemPedido;
import com.nelioalves.cursomc.domain.domainPagamentoComBoleto;
import com.nelioalves.cursomc.domain.domainPedido;
import com.nelioalves.cursomc.domain.enums.enumEstadoPagamento;
import com.nelioalves.cursomc.repositories.repositoryItemPedido;
import com.nelioalves.cursomc.repositories.repositoryPagamento;
import com.nelioalves.cursomc.repositories.repositoryPedido;
import com.nelioalves.cursomc.services.exception.service_exceptionGenericRuntimeException;
import com.nelioalves.cursomc.services.utils.serviceUtils_Boleto;

@Service
public class servicePedido {

	@Autowired
	private repositoryPedido repoPedido;
	
	@Autowired
	private repositoryPagamento repoPagamento;
	
	@Autowired
	private serviceUtils_Boleto serviceUtils_Boleto;

	@Autowired
	private serviceProduto serviceProduto;

	@Autowired
	private repositoryItemPedido repoItemPedido;

	public domainPedido service_find(Integer Id) {
		Optional<domainPedido> obj = repoPedido.findById(Id);
		return obj.orElseThrow(() -> new service_exceptionGenericRuntimeException("Objeto não encontrado! Id: " + Id + ", Tipo: " + domainPedido.class.getName()));
	}
	
	public domainPedido service_insert(domainPedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(enumEstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof domainPagamentoComBoleto) {
			domainPagamentoComBoleto pgto = (domainPagamentoComBoleto) obj.getPagamento();
			serviceUtils_Boleto.PreencherPagtoComBoleto(pgto, obj.getInstante());
		}
		repoPedido.save(obj);
		repoPagamento.save(obj.getPagamento());
		for (domainItemPedido ip: obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(serviceProduto.service_find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		repoItemPedido.saveAll(obj.getItens());
		return obj;
	}
	
}
