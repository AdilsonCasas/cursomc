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
import com.nelioalves.cursomc.domain.enums.enumEstadoPagamento;
import com.nelioalves.cursomc.domain.enums.enumTipoCliente;
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

		domainCategoria cat1 = new domainCategoria(null, "Informática");
		domainCategoria cat2 = new domainCategoria(null, "Escritório");
		domainCategoria cat3 = new domainCategoria(null, "Cama mesa e banho");
		domainCategoria cat4 = new domainCategoria(null, "Eletrônicos");
		domainCategoria cat5 = new domainCategoria(null, "Jardinagem");
		domainCategoria cat6 = new domainCategoria(null, "Decoração");
		domainCategoria cat7 = new domainCategoria(null, "Perfumaria");
		
		domainProduto prod1 = new domainProduto(null, "Computador",        2000.00);
		domainProduto prod2 = new domainProduto(null, "Impressora",         800.00);
		domainProduto prod3 = new domainProduto(null, "Mouse",               80.00);
		domainProduto prod4 = new domainProduto(null, "Mesa de Escritório", 300.00);
		domainProduto prod5 = new domainProduto(null, "Toalha",              50.00);
		domainProduto prod6 = new domainProduto(null, "Colcha",             200.00);
		domainProduto prod7 = new domainProduto(null, "TV true color",     1200.00);
		domainProduto prod8 = new domainProduto(null, "Roçadeira",          800.00);
		domainProduto prod9 = new domainProduto(null, "Abajour",            100.00);
		domainProduto prod10 = new domainProduto(null, "Pendente",          180.00);
		domainProduto prod11 = new domainProduto(null, "Shampoo",            90.00);
		
		cat1.getProdutos().addAll(Arrays.asList(prod1,prod2,prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2,prod4));
		cat3.getProdutos().addAll(Arrays.asList(prod5,prod6));
		cat4.getProdutos().addAll(Arrays.asList(prod1,prod2,prod3,prod7));
		cat5.getProdutos().addAll(Arrays.asList(prod8));
		cat6.getProdutos().addAll(Arrays.asList(prod9,prod10));
		cat7.getProdutos().addAll(Arrays.asList(prod11));
		
		prod1.getCategorias().addAll(Arrays.asList(cat1,cat4));
		prod2.getCategorias().addAll(Arrays.asList(cat1,cat2,cat4));
		prod3.getCategorias().addAll(Arrays.asList(cat1));
		prod4.getCategorias().addAll(Arrays.asList(cat2));
		prod5.getCategorias().addAll(Arrays.asList(cat3));
		prod6.getCategorias().addAll(Arrays.asList(cat3));
		prod7.getCategorias().addAll(Arrays.asList(cat4));
		prod8.getCategorias().addAll(Arrays.asList(cat5));
		prod9.getCategorias().addAll(Arrays.asList(cat6));
		prod10.getCategorias().addAll(Arrays.asList(cat6));
		prod11.getCategorias().addAll(Arrays.asList(cat7));
		
		categoriaRepo.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepo.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10, prod11));

		domainEstado uf1 = new domainEstado(null,"Minas Gerais");
		domainEstado uf2 = new domainEstado(null,"São Paulo");
		
		domainCidade cid1 = new domainCidade(null, "Uberlândia", uf1);
		domainCidade cid2 = new domainCidade(null, "São Paulo", uf2);
		domainCidade cid3 = new domainCidade(null, "Campinas", uf2);
		
		uf1.getCidades().addAll(Arrays.asList(cid1));
		uf2.getCidades().addAll(Arrays.asList(cid2,cid3));
		
		estadoRepo.saveAll(Arrays.asList(uf1, uf2));
		cidadeRepo.saveAll(Arrays.asList(cid1, cid2, cid3));
		
		domainCliente cli1 = new domainCliente(null, "Maria Silva", "maria@gmail.com", "36378912377", enumTipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		domainEndereco e1 = new domainEndereco(null, "Rua Flores", "300", "Ap 303", "Jardins", "38220830", cli1, cid1);
		domainEndereco e2 = new domainEndereco(null, "Av. Matos", "105", "Sala 800", "Centro", "38777012", cli1, cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepo.saveAll(Arrays.asList(cli1));
		enderecoRepo.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		domainPedido ped1 = new domainPedido(null, simpleDateFormat.parse("30/09/2017 10:32"),cli1, e1);
		domainPedido ped2 = new domainPedido(null, simpleDateFormat.parse("10/10/2017 19:35"),cli1, e2);
		
		domainPagamento pagto1 = new domainPagamentoComCartao(null, enumEstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		domainPagamento pagto2 = new domainPagamentoComBoleto(null, enumEstadoPagamento.PENDENTE, ped2, simpleDateFormat.parse("20/10/2017 00:99"), null);
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
