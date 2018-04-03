package com.nelioalves.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomc.domain.domainCategoria;
import com.nelioalves.cursomc.domain.domainCidade;
import com.nelioalves.cursomc.domain.domainCliente;
import com.nelioalves.cursomc.domain.domainEndereco;
import com.nelioalves.cursomc.domain.domainEstado;
import com.nelioalves.cursomc.domain.domainItemPedido;
import com.nelioalves.cursomc.domain.domainPagamento;
import com.nelioalves.cursomc.domain.domainPagamentoComBoleto;
import com.nelioalves.cursomc.domain.domainPagamentoComCartao;
import com.nelioalves.cursomc.domain.domainPedido;
import com.nelioalves.cursomc.domain.domainProduto;
import com.nelioalves.cursomc.domain.enums.EstadoPagamento;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.repositories.repositoryCategoria;
import com.nelioalves.cursomc.repositories.repositoryCidade;
import com.nelioalves.cursomc.repositories.repositoryCliente;
import com.nelioalves.cursomc.repositories.repositoryEndereco;
import com.nelioalves.cursomc.repositories.repositoryEstado;
import com.nelioalves.cursomc.repositories.repositoryItemPedido;
import com.nelioalves.cursomc.repositories.repositoryPagamento;
import com.nelioalves.cursomc.repositories.repositoryPedido;
import com.nelioalves.cursomc.repositories.repositoryProduto;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private repositoryCategoria categoriaRepo;
	@Autowired
	private repositoryProduto produtoRepo;
	@Autowired
	private repositoryEstado estadoRepo;
	@Autowired
	private repositoryCidade cidadeRepo;
	@Autowired
	private repositoryCliente clienteRepo;
	@Autowired
	private repositoryEndereco enderecoRepo;
	@Autowired
	private repositoryPedido pedidoRepo;
	@Autowired
	private repositoryPagamento pagamentoRepo;
	@Autowired
	private repositoryItemPedido itemPedidoRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Categoria+Produto
		domainCategoria cat1 = new domainCategoria(null, "Informática");
		domainCategoria cat2 = new domainCategoria(null, "Escritório");
		
		domainProduto prod1 = new domainProduto(null, "Computador", 2000.00);
		domainProduto prod2 = new domainProduto(null, "Impressora",  800.00);
		domainProduto prod3 = new domainProduto(null, "Mouse",        80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(prod1,prod2,prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2));
		
		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepo.saveAll(Arrays.asList(cat1, cat2));
		produtoRepo.saveAll(Arrays.asList(prod1, prod2, prod3));

		//Estado+Cidade
	
		domainEstado est1 = new domainEstado(null,"Minas Gerais");
		domainEstado est2 = new domainEstado(null,"São Paulo");
		
		domainCidade c1 = new domainCidade(null, "Uberlândia", est1);
		domainCidade c2 = new domainCidade(null, "São Paulo", est2);
		domainCidade c3 = new domainCidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepo.saveAll(Arrays.asList(est1, est2));
		cidadeRepo.saveAll(Arrays.asList(c1, c2, c3));
		
		domainCliente cli1 = new domainCliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		domainEndereco e1 = new domainEndereco(null, "Rua Flores", "300", "Ap 303", "Jardins", "38220830", cli1, c1);
		domainEndereco e2 = new domainEndereco(null, "Av. Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepo.saveAll(Arrays.asList(cli1));
		enderecoRepo.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		domainPedido ped1 = new domainPedido(null, simpleDateFormat.parse("30/09/2017 10:32"),cli1, e1);
		domainPedido ped2 = new domainPedido(null, simpleDateFormat.parse("10/10/2017 19:35"),cli1, e2);
		
		domainPagamento pagto1 = new domainPagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		domainPagamento pagto2 = new domainPagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, simpleDateFormat.parse("20/10/2017 00:99"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepo.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepo.saveAll(Arrays.asList(pagto1,pagto2));
		
		domainItemPedido itemPed1 = new domainItemPedido(ped1, prod1, 0.00, 1, 2000.00);
		domainItemPedido itemPed2 = new domainItemPedido(ped1, prod3, 0.00, 2, 80.00);
		domainItemPedido itemPed3 = new domainItemPedido(ped2, prod2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(itemPed1,itemPed2));
		ped2.getItens().addAll(Arrays.asList(itemPed3));
		
		prod1.getItens().addAll(Arrays.asList(itemPed1));
		prod2.getItens().addAll(Arrays.asList(itemPed3));
		prod3.getItens().addAll(Arrays.asList(itemPed2));
		
		itemPedidoRepo.saveAll(Arrays.asList(itemPed1,itemPed2,itemPed3));
	}
}
