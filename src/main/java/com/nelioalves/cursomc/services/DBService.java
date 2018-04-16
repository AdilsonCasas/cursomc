package com.nelioalves.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.CategoriaEntity;
import com.nelioalves.cursomc.domain.CidadeEntity;
import com.nelioalves.cursomc.domain.ClienteEntity;
import com.nelioalves.cursomc.domain.EnderecoEntity;
import com.nelioalves.cursomc.domain.EstadoEntity;
import com.nelioalves.cursomc.domain.ItemPedidoEntity;
import com.nelioalves.cursomc.domain.PagamentoComBoletoEntity;
import com.nelioalves.cursomc.domain.PagamentoComCartaoEntity;
import com.nelioalves.cursomc.domain.PagamentoEntity;
import com.nelioalves.cursomc.domain.PedidoEntity;
import com.nelioalves.cursomc.domain.ProdutoEntity;
import com.nelioalves.cursomc.domain.enums.enumEstadoPagamento;
import com.nelioalves.cursomc.domain.enums.enumPerfilUsuario;
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

		CategoriaEntity var_cat1 = new CategoriaEntity(null, "Informática");
		CategoriaEntity var_cat2 = new CategoriaEntity(null, "Escritório");
		CategoriaEntity var_cat3 = new CategoriaEntity(null, "Cama mesa e banho");
		CategoriaEntity var_cat4 = new CategoriaEntity(null, "Eletrônicos");
		CategoriaEntity var_cat5 = new CategoriaEntity(null, "Jardinagem");
		CategoriaEntity var_cat6 = new CategoriaEntity(null, "Decoração");
		CategoriaEntity var_cat7 = new CategoriaEntity(null, "Perfumaria");
		
		ProdutoEntity var_prod1  = new ProdutoEntity(null, "Computador",        2000.00);
		ProdutoEntity var_prod2  = new ProdutoEntity(null, "Impressora",         800.00);
		ProdutoEntity var_prod3  = new ProdutoEntity(null, "Mouse",               80.00);
		ProdutoEntity var_prod4  = new ProdutoEntity(null, "Mesa de Escritório", 300.00);
		ProdutoEntity var_prod5  = new ProdutoEntity(null, "Toalha",              50.00);
		ProdutoEntity var_prod6  = new ProdutoEntity(null, "Colcha",             200.00);
		ProdutoEntity var_prod7  = new ProdutoEntity(null, "TV true color",     1200.00);
		ProdutoEntity var_prod8  = new ProdutoEntity(null, "Roçadeira",          800.00);
		ProdutoEntity var_prod9  = new ProdutoEntity(null, "Abajour",            100.00);
		ProdutoEntity var_prod10 = new ProdutoEntity(null, "Pendente",          180.00);
		ProdutoEntity var_prod11 = new ProdutoEntity(null, "Shampoo",            90.00);
		
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

		EstadoEntity var_uf1 = new EstadoEntity(null,"Minas Gerais");
		EstadoEntity var_uf2 = new EstadoEntity(null,"São Paulo");
		
		CidadeEntity var_cid1 = new CidadeEntity(null, "Uberlândia", var_uf1);
		CidadeEntity var_cid2 = new CidadeEntity(null, "São Paulo", var_uf2);
		CidadeEntity var_cid3 = new CidadeEntity(null, "Campinas", var_uf2);
		
		var_uf1.getCidades().addAll(Arrays.asList(var_cid1));
		var_uf2.getCidades().addAll(Arrays.asList(var_cid2,var_cid3));
		
		var_repoEstado.saveAll(Arrays.asList(var_uf1, var_uf2));
		var_repoCidade.saveAll(Arrays.asList(var_cid1, var_cid2, var_cid3));
		
		ClienteEntity var_cli1 = new ClienteEntity(null, "Maria Silva", "adilson.casas@gmail.com", "36378912377", enumTipoCliente.PESSOAFISICA, var_bCryptPasswordEncoder.encode("123"));
		var_cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		ClienteEntity var_cli2 = new ClienteEntity(null, "Ana Costa", "pp890645@gmail.com", "69254717509", enumTipoCliente.PESSOAFISICA, var_bCryptPasswordEncoder.encode("123"));
		var_cli2.getTelefones().addAll(Arrays.asList("93883321","34252625"));
		var_cli2.addPerfil(enumPerfilUsuario.ADMIN); // vou assumir como regra de negácio que todo usuário do meu sistema será um "Cliente", dentre estes, alguns, também serão ADMIN

		
		EnderecoEntity var_e1 = new EnderecoEntity(null, "Rua Flores", "300", "Ap 303", "Jardins", "38220830", var_cli1, var_cid1);
		EnderecoEntity var_e2 = new EnderecoEntity(null, "Av. Matos", "105", "Sala 800", "Centro", "38777012", var_cli1, var_cid2);
		EnderecoEntity var_e3 = new EnderecoEntity(null, "Av. Floriano", "2106", null, "Centro", "28177012", var_cli2, var_cid2);
		
		var_cli1.getEnderecos().addAll(Arrays.asList(var_e1,var_e2));
		var_cli2.getEnderecos().addAll(Arrays.asList(var_e3));
		
		var_repoCliente.saveAll(Arrays.asList(var_cli1,var_cli2));
		var_repoEndereco.saveAll(Arrays.asList(var_e1,var_e2,var_e3));
		
		SimpleDateFormat var_simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		PedidoEntity var_ped1 = new PedidoEntity(null, var_simpleDateFormat.parse("30/09/2017 10:32"),var_cli1, var_e1);
		PedidoEntity var_ped2 = new PedidoEntity(null, var_simpleDateFormat.parse("10/10/2017 19:35"),var_cli1, var_e2);
		
		PagamentoEntity var_pagto1 = new PagamentoComCartaoEntity(null, enumEstadoPagamento.QUITADO, var_ped1, 6);
		var_ped1.setPagamento(var_pagto1);
		
		PagamentoEntity var_pagto2 = new PagamentoComBoletoEntity(null, enumEstadoPagamento.PENDENTE, var_ped2, var_simpleDateFormat.parse("20/10/2017 00:99"), null);
		var_ped2.setPagamento(var_pagto2);
		
		var_cli1.getPedidos().addAll(Arrays.asList(var_ped1,var_ped2));
		
		var_repoPedido.saveAll(Arrays.asList(var_ped1,var_ped2));
		var_repoPagamento.saveAll(Arrays.asList(var_pagto1,var_pagto2));
		
		ItemPedidoEntity var_itemPed1 = new ItemPedidoEntity(var_ped1, var_prod1, 0.00, 1, 2000.00);
		ItemPedidoEntity var_itemPed2 = new ItemPedidoEntity(var_ped1, var_prod3, 0.00, 2, 80.00);
		ItemPedidoEntity var_itemPed3 = new ItemPedidoEntity(var_ped2, var_prod2, 100.00, 1, 800.00);
		
		var_ped1.getItens().addAll(Arrays.asList(var_itemPed1,var_itemPed2));
		var_ped2.getItens().addAll(Arrays.asList(var_itemPed3));
		
		var_prod1.getItens().addAll(Arrays.asList(var_itemPed1));
		var_prod2.getItens().addAll(Arrays.asList(var_itemPed3));
		var_prod3.getItens().addAll(Arrays.asList(var_itemPed2));
		
		var_repoItemPedido.saveAll(Arrays.asList(var_itemPed1,var_itemPed2,var_itemPed3));

	}

}
