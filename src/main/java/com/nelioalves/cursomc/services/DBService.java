package com.nelioalves.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.CategoriaDomain;
import com.nelioalves.cursomc.domain.CidadeDomain;
import com.nelioalves.cursomc.domain.ClienteDomain;
import com.nelioalves.cursomc.domain.EnderecoDomain;
import com.nelioalves.cursomc.domain.EstadoDomain;
import com.nelioalves.cursomc.domain.ItemPedidoDomain;
import com.nelioalves.cursomc.domain.PagamentoComBoletoDomain;
import com.nelioalves.cursomc.domain.PagamentoComCartaoDomain;
import com.nelioalves.cursomc.domain.PagamentoDomain;
import com.nelioalves.cursomc.domain.PedidoDomain;
import com.nelioalves.cursomc.domain.ProdutoDomain;
import com.nelioalves.cursomc.domain.enums.enumEstadoPagamento;
import com.nelioalves.cursomc.domain.enums.enumTipoCliente;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.repositories.EstadoRepository;
import com.nelioalves.cursomc.repositories.ItemPedidoRepository;
import com.nelioalves.cursomc.repositories.PagamentoRepository;
import com.nelioalves.cursomc.repositories.PedidoRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder var_bCryptPasswordEncoder;

	@Autowired
	private CategoriaRepository var_repoCategoria;
	@Autowired
	private ProdutoRepository var_repoProduto;
	@Autowired
	private EstadoRepository var_repoEstado;
	@Autowired
	private CidadeRepository var_repoCidade;
	@Autowired
	private ClienteRepository var_repoCliente;
	@Autowired
	private EnderecoRepository var_repoEndereco;
	@Autowired
	private PedidoRepository var_repoPedido;
	@Autowired
	private PagamentoRepository var_repoPagamento;
	@Autowired
	private ItemPedidoRepository var_repoItemPedido;
	
	public void metodoService_instantiateTestDatabase() throws ParseException {

		CategoriaDomain var_cat1 = new CategoriaDomain(null, "Informática");
		CategoriaDomain var_cat2 = new CategoriaDomain(null, "Escritório");
		CategoriaDomain var_cat3 = new CategoriaDomain(null, "Cama mesa e banho");
		CategoriaDomain var_cat4 = new CategoriaDomain(null, "Eletrônicos");
		CategoriaDomain var_cat5 = new CategoriaDomain(null, "Jardinagem");
		CategoriaDomain var_cat6 = new CategoriaDomain(null, "Decoração");
		CategoriaDomain var_cat7 = new CategoriaDomain(null, "Perfumaria");
		
		ProdutoDomain var_prod1  = new ProdutoDomain(null, "Computador",        2000.00);
		ProdutoDomain var_prod2  = new ProdutoDomain(null, "Impressora",         800.00);
		ProdutoDomain var_prod3  = new ProdutoDomain(null, "Mouse",               80.00);
		ProdutoDomain var_prod4  = new ProdutoDomain(null, "Mesa de Escritório", 300.00);
		ProdutoDomain var_prod5  = new ProdutoDomain(null, "Toalha",              50.00);
		ProdutoDomain var_prod6  = new ProdutoDomain(null, "Colcha",             200.00);
		ProdutoDomain var_prod7  = new ProdutoDomain(null, "TV true color",     1200.00);
		ProdutoDomain var_prod8  = new ProdutoDomain(null, "Roçadeira",          800.00);
		ProdutoDomain var_prod9  = new ProdutoDomain(null, "Abajour",            100.00);
		ProdutoDomain var_prod10 = new ProdutoDomain(null, "Pendente",          180.00);
		ProdutoDomain var_prod11 = new ProdutoDomain(null, "Shampoo",            90.00);
		
		var_cat1.getProdutos().addAll(Arrays.asList(var_prod1,var_prod2,var_prod3));
		var_cat2.getProdutos().addAll(Arrays.asList(var_prod2,var_prod4));
		var_cat3.getProdutos().addAll(Arrays.asList(var_prod5,var_prod6));
		var_cat4.getProdutos().addAll(Arrays.asList(var_prod1,var_prod2,var_prod3,var_prod7));
		var_cat5.getProdutos().addAll(Arrays.asList(var_prod8));
		var_cat6.getProdutos().addAll(Arrays.asList(var_prod9,var_prod10));
		var_cat7.getProdutos().addAll(Arrays.asList(var_prod11));
		
		var_prod1.getCategorias().addAll(Arrays.asList(var_cat1,var_cat4));
		var_prod2.getCategorias().addAll(Arrays.asList(var_cat1,var_cat2,var_cat4));
		var_prod3.getCategorias().addAll(Arrays.asList(var_cat1));
		var_prod4.getCategorias().addAll(Arrays.asList(var_cat2));
		var_prod5.getCategorias().addAll(Arrays.asList(var_cat3));
		var_prod6.getCategorias().addAll(Arrays.asList(var_cat3));
		var_prod7.getCategorias().addAll(Arrays.asList(var_cat4));
		var_prod8.getCategorias().addAll(Arrays.asList(var_cat5));
		var_prod9.getCategorias().addAll(Arrays.asList(var_cat6));
		var_prod10.getCategorias().addAll(Arrays.asList(var_cat6));
		var_prod11.getCategorias().addAll(Arrays.asList(var_cat7));
		
		var_repoCategoria.saveAll(Arrays.asList(var_cat1, var_cat2, var_cat3, var_cat4, var_cat5, var_cat6, var_cat7));
		var_repoProduto.saveAll(Arrays.asList(var_prod1, var_prod2, var_prod3, var_prod4, var_prod5, var_prod6, var_prod7, var_prod8, var_prod9, var_prod10, var_prod11));

		EstadoDomain var_uf1 = new EstadoDomain(null,"Minas Gerais");
		EstadoDomain var_uf2 = new EstadoDomain(null,"São Paulo");
		
		CidadeDomain var_cid1 = new CidadeDomain(null, "Uberlândia", var_uf1);
		CidadeDomain var_cid2 = new CidadeDomain(null, "São Paulo", var_uf2);
		CidadeDomain var_cid3 = new CidadeDomain(null, "Campinas", var_uf2);
		
		var_uf1.getCidades().addAll(Arrays.asList(var_cid1));
		var_uf2.getCidades().addAll(Arrays.asList(var_cid2,var_cid3));
		
		var_repoEstado.saveAll(Arrays.asList(var_uf1, var_uf2));
		var_repoCidade.saveAll(Arrays.asList(var_cid1, var_cid2, var_cid3));
		
		ClienteDomain var_cli1 = new ClienteDomain(null, "Maria Silva", "adilson.casas@gmail.com", "36378912377", enumTipoCliente.PESSOAFISICA, var_bCryptPasswordEncoder.encode("123"));
		
		var_cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		EnderecoDomain var_e1 = new EnderecoDomain(null, "Rua Flores", "300", "Ap 303", "Jardins", "38220830", var_cli1, var_cid1);
		EnderecoDomain var_e2 = new EnderecoDomain(null, "Av. Matos", "105", "Sala 800", "Centro", "38777012", var_cli1, var_cid2);
		
		var_cli1.getEnderecos().addAll(Arrays.asList(var_e1,var_e2));
		
		var_repoCliente.saveAll(Arrays.asList(var_cli1));
		var_repoEndereco.saveAll(Arrays.asList(var_e1,var_e2));
		
		SimpleDateFormat var_simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		PedidoDomain var_ped1 = new PedidoDomain(null, var_simpleDateFormat.parse("30/09/2017 10:32"),var_cli1, var_e1);
		PedidoDomain var_ped2 = new PedidoDomain(null, var_simpleDateFormat.parse("10/10/2017 19:35"),var_cli1, var_e2);
		
		PagamentoDomain var_pagto1 = new PagamentoComCartaoDomain(null, enumEstadoPagamento.QUITADO, var_ped1, 6);
		var_ped1.setPagamento(var_pagto1);
		
		PagamentoDomain var_pagto2 = new PagamentoComBoletoDomain(null, enumEstadoPagamento.PENDENTE, var_ped2, var_simpleDateFormat.parse("20/10/2017 00:99"), null);
		var_ped2.setPagamento(var_pagto2);
		
		var_cli1.getPedidos().addAll(Arrays.asList(var_ped1,var_ped2));
		
		var_repoPedido.saveAll(Arrays.asList(var_ped1,var_ped2));
		var_repoPagamento.saveAll(Arrays.asList(var_pagto1,var_pagto2));
		
		ItemPedidoDomain var_itemPed1 = new ItemPedidoDomain(var_ped1, var_prod1, 0.00, 1, 2000.00);
		ItemPedidoDomain var_itemPed2 = new ItemPedidoDomain(var_ped1, var_prod3, 0.00, 2, 80.00);
		ItemPedidoDomain var_itemPed3 = new ItemPedidoDomain(var_ped2, var_prod2, 100.00, 1, 800.00);
		
		var_ped1.getItens().addAll(Arrays.asList(var_itemPed1,var_itemPed2));
		var_ped2.getItens().addAll(Arrays.asList(var_itemPed3));
		
		var_prod1.getItens().addAll(Arrays.asList(var_itemPed1));
		var_prod2.getItens().addAll(Arrays.asList(var_itemPed3));
		var_prod3.getItens().addAll(Arrays.asList(var_itemPed2));
		
		var_repoItemPedido.saveAll(Arrays.asList(var_itemPed1,var_itemPed2,var_itemPed3));

	}

}
