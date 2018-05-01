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
		ProdutoEntity var_prod10 = new ProdutoEntity(null, "Pendente",           180.00);
		ProdutoEntity var_prod11 = new ProdutoEntity(null, "Shampoo 11",          90.00);
		ProdutoEntity var_prod12 = new ProdutoEntity(null, "Shampoo 12",          90.00);
		ProdutoEntity var_prod13 = new ProdutoEntity(null, "Shampoo 13",          90.00);
		ProdutoEntity var_prod14 = new ProdutoEntity(null, "Shampoo 14",          90.00);
		ProdutoEntity var_prod15 = new ProdutoEntity(null, "Shampoo 15",          90.00);
		ProdutoEntity var_prod16 = new ProdutoEntity(null, "Shampoo 16",          90.00);
		ProdutoEntity var_prod17 = new ProdutoEntity(null, "Shampoo 17",          90.00);
		ProdutoEntity var_prod18 = new ProdutoEntity(null, "Shampoo 18",          90.00);
		ProdutoEntity var_prod19 = new ProdutoEntity(null, "Shampoo 19",          90.00);
		ProdutoEntity var_prod20 = new ProdutoEntity(null, "Shampoo 20",          90.00);
		ProdutoEntity var_prod21 = new ProdutoEntity(null, "Shampoo 21",          90.00);
		ProdutoEntity var_prod22 = new ProdutoEntity(null, "Shampoo 22",          90.00);
		ProdutoEntity var_prod23 = new ProdutoEntity(null, "Shampoo 23",          90.00);
		ProdutoEntity var_prod24 = new ProdutoEntity(null, "Shampoo 24",          90.00);
		ProdutoEntity var_prod25 = new ProdutoEntity(null, "Shampoo 25",          90.00);
		ProdutoEntity var_prod26 = new ProdutoEntity(null, "Shampoo 26",          90.00);
		ProdutoEntity var_prod27 = new ProdutoEntity(null, "Shampoo 27",          90.00);
		ProdutoEntity var_prod28 = new ProdutoEntity(null, "Shampoo 28",          90.00);
		ProdutoEntity var_prod29 = new ProdutoEntity(null, "Shampoo 29",          90.00);
		ProdutoEntity var_prod30 = new ProdutoEntity(null, "Shampoo 30",          90.00);
		ProdutoEntity var_prod31 = new ProdutoEntity(null, "Shampoo 31",          90.00);
		ProdutoEntity var_prod32 = new ProdutoEntity(null, "Shampoo 32",          90.00);
		ProdutoEntity var_prod33 = new ProdutoEntity(null, "Shampoo 33",          90.00);
		ProdutoEntity var_prod34 = new ProdutoEntity(null, "Shampoo 34",          90.00);
		ProdutoEntity var_prod35 = new ProdutoEntity(null, "Shampoo 35",          90.00);
		ProdutoEntity var_prod36 = new ProdutoEntity(null, "Shampoo 36",          90.00);
		ProdutoEntity var_prod37 = new ProdutoEntity(null, "Shampoo 37",          90.00);
		ProdutoEntity var_prod38 = new ProdutoEntity(null, "Shampoo 38",          90.00);
		ProdutoEntity var_prod39 = new ProdutoEntity(null, "Shampoo 39",          90.00);
		ProdutoEntity var_prod40 = new ProdutoEntity(null, "Shampoo 40",          90.00);
		ProdutoEntity var_prod41 = new ProdutoEntity(null, "Shampoo 41",          90.00);
		ProdutoEntity var_prod42 = new ProdutoEntity(null, "Shampoo 42",          90.00);
		ProdutoEntity var_prod43 = new ProdutoEntity(null, "Shampoo 43",          90.00);
		ProdutoEntity var_prod44 = new ProdutoEntity(null, "Shampoo 44",          90.00);
		ProdutoEntity var_prod45 = new ProdutoEntity(null, "Shampoo 45",          90.00);
		ProdutoEntity var_prod46 = new ProdutoEntity(null, "Shampoo 46",          90.00);
		ProdutoEntity var_prod47 = new ProdutoEntity(null, "Shampoo 47",          90.00);
		ProdutoEntity var_prod48 = new ProdutoEntity(null, "Shampoo 48",          90.00);
		ProdutoEntity var_prod49 = new ProdutoEntity(null, "Shampoo 49",          90.00);
		ProdutoEntity var_prod50 = new ProdutoEntity(null, "Shampoo 50",          90.00);
		ProdutoEntity var_prod51 = new ProdutoEntity(null, "Shampoo 51",          90.00);
		ProdutoEntity var_prod52 = new ProdutoEntity(null, "Shampoo 52",          90.00);

		var_cat1.getProdutos().addAll(Arrays.asList(var_prod1,var_prod2,var_prod3));
		var_cat2.getProdutos().addAll(Arrays.asList(var_prod2,var_prod4));
		var_cat3.getProdutos().addAll(Arrays.asList(var_prod5,var_prod6));
		var_cat4.getProdutos().addAll(Arrays.asList(var_prod1,var_prod2,var_prod3,var_prod7));
		var_cat5.getProdutos().addAll(Arrays.asList(var_prod8));
		var_cat6.getProdutos().addAll(Arrays.asList(var_prod9,var_prod10));
		var_cat7.getProdutos().addAll(Arrays.asList(  var_prod11, var_prod12, var_prod13, var_prod14, var_prod15, var_prod16, var_prod17, var_prod18, var_prod19
				, var_prod20, var_prod21, var_prod22, var_prod23, var_prod24, var_prod25, var_prod26, var_prod27, var_prod28, var_prod29, var_prod30, var_prod31
				, var_prod32, var_prod33, var_prod34, var_prod35, var_prod36, var_prod37, var_prod38, var_prod39, var_prod40, var_prod41, var_prod42, var_prod43
				, var_prod44, var_prod45, var_prod46, var_prod47, var_prod48, var_prod49, var_prod50, var_prod51, var_prod52));
		
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
		var_prod12.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod13.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod14.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod15.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod16.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod17.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod18.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod19.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod20.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod21.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod22.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod23.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod24.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod25.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod26.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod27.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod28.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod29.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod30.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod31.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod32.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod33.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod34.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod35.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod36.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod37.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod38.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod39.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod40.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod41.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod42.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod43.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod44.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod45.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod46.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod47.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod48.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod49.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod50.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod51.getCategorias().addAll(Arrays.asList(var_cat7));
		var_prod52.getCategorias().addAll(Arrays.asList(var_cat7));
		
		var_repoCategoria.saveAll(Arrays.asList(var_cat1, var_cat2, var_cat3, var_cat4, var_cat5, var_cat6, var_cat7));
		var_repoProduto.saveAll(Arrays.asList(       var_prod1,  var_prod2,  var_prod3,  var_prod4,  var_prod5,  var_prod6,  var_prod7,  var_prod8,  var_prod9, 
				var_prod10,	var_prod11, var_prod12, var_prod13, var_prod14, var_prod15, var_prod16, var_prod17, var_prod18, var_prod19, var_prod20, var_prod21, 
				var_prod22, var_prod23, var_prod24, var_prod25, var_prod26, var_prod27, var_prod28, var_prod29, var_prod30, var_prod31, var_prod32, var_prod33,
				var_prod34, var_prod35, var_prod36, var_prod37, var_prod38, var_prod39, var_prod40, var_prod41, var_prod42, var_prod43, var_prod44, var_prod45, 
				var_prod46, var_prod47, var_prod48, var_prod49, var_prod50, var_prod51, var_prod52));

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
		var_cli2.addPerfil(enumPerfilUsuario.ADMIN); // vou assumir como regra de negócio que todo usuário do meu sistema será um "Cliente", dentre estes, alguns, também serão ADMIN

		EnderecoEntity var_e1 = new EnderecoEntity(null, "Rua Flores", "300", "Ap 303", "Jardins", "38220830", var_cli1, var_cid1);
		EnderecoEntity var_e2 = new EnderecoEntity(null, "Av. Matos", "105", "Sala 800", "Centro", "38777012", var_cli1, var_cid2);
		EnderecoEntity var_e3 = new EnderecoEntity(null, "Av. Floriano", "2106", null, "Centro", "28177012", var_cli2, var_cid2);
		
		var_cli1.getEnderecos().addAll(Arrays.asList(var_e1,var_e2));
		var_cli2.getEnderecos().addAll(Arrays.asList(var_e3));
		
		var_repoCliente.saveAll(Arrays.asList(var_cli1,var_cli2));
		var_repoEndereco.saveAll(Arrays.asList(var_e1,var_e2,var_e3));
		
		SimpleDateFormat var_simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // usar "dd/MM/yyyy", não "dd/mm/yyyy" (MM = mês, mm = minuto)
		PedidoEntity var_ped1;
		try {
			var_ped1 = new PedidoEntity(null, var_simpleDateFormat.parse("30/09/2017 10:32"),var_cli1, var_e1);
		} catch (ParseException e) {
			throw new ParseException("ERRO_PADRAO#9999@Processando Data Ped_1: "+e.getMessage(),1);
		}
		PedidoEntity var_ped2;
		try {
			var_ped2 = new PedidoEntity(null, var_simpleDateFormat.parse("10/10/2017 19:35"),var_cli1, var_e2);
		} catch (ParseException e) {
			throw new ParseException("ERRO_PADRAO#9999@Processando Data Ped_2: "+e.getMessage(),1);
		}
		
		PagamentoEntity var_pagto1 = new PagamentoComCartaoEntity(null, enumEstadoPagamento.QUITADO, var_ped1, 6);
		var_ped1.setPagamento(var_pagto1);
		
		PagamentoEntity var_pagto2=null;
		try {
			var_pagto2 = new PagamentoComBoletoEntity(null, enumEstadoPagamento.PENDENTE, var_ped2, var_simpleDateFormat.parse("20/10/2017 00:99"), null);
		} catch (ParseException e) {
			throw new ParseException("ERRO_PADRAO#9999@Processando Data Pagto 2: "+e.getMessage(),1);
		}
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
