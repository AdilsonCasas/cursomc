package com.nelioalves.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
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
	private CategoriaRepository repoCategoria;
	@Autowired
	private ProdutoRepository repoProduto;
	@Autowired
	private EstadoRepository repoEstado;
	@Autowired
	private CidadeRepository repoCidade;
	@Autowired
	private ClienteRepository repoCliente;
	@Autowired
	private EnderecoRepository repoEndereco;
	@Autowired
	private PedidoRepository repoPedido;
	@Autowired
	private PagamentoRepository repoPagamento;
	@Autowired
	private ItemPedidoRepository repoItemPedido;
	
	public void instantiateTestDatabase() throws ParseException {

		CategoriaDomain cat1 = new CategoriaDomain(null, "Informática");
		CategoriaDomain cat2 = new CategoriaDomain(null, "Escritório");
		CategoriaDomain cat3 = new CategoriaDomain(null, "Cama mesa e banho");
		CategoriaDomain cat4 = new CategoriaDomain(null, "Eletrônicos");
		CategoriaDomain cat5 = new CategoriaDomain(null, "Jardinagem");
		CategoriaDomain cat6 = new CategoriaDomain(null, "Decoração");
		CategoriaDomain cat7 = new CategoriaDomain(null, "Perfumaria");
		
		ProdutoDomain prod1  = new ProdutoDomain(null, "Computador",        2000.00);
		ProdutoDomain prod2  = new ProdutoDomain(null, "Impressora",         800.00);
		ProdutoDomain prod3  = new ProdutoDomain(null, "Mouse",               80.00);
		ProdutoDomain prod4  = new ProdutoDomain(null, "Mesa de Escritório", 300.00);
		ProdutoDomain prod5  = new ProdutoDomain(null, "Toalha",              50.00);
		ProdutoDomain prod6  = new ProdutoDomain(null, "Colcha",             200.00);
		ProdutoDomain prod7  = new ProdutoDomain(null, "TV true color",     1200.00);
		ProdutoDomain prod8  = new ProdutoDomain(null, "Roçadeira",          800.00);
		ProdutoDomain prod9  = new ProdutoDomain(null, "Abajour",            100.00);
		ProdutoDomain prod10 = new ProdutoDomain(null, "Pendente",          180.00);
		ProdutoDomain prod11 = new ProdutoDomain(null, "Shampoo",            90.00);
		
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
		
		repoCategoria.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		repoProduto.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10, prod11));

		EstadoDomain uf1 = new EstadoDomain(null,"Minas Gerais");
		EstadoDomain uf2 = new EstadoDomain(null,"São Paulo");
		
		CidadeDomain cid1 = new CidadeDomain(null, "Uberlândia", uf1);
		CidadeDomain cid2 = new CidadeDomain(null, "São Paulo", uf2);
		CidadeDomain cid3 = new CidadeDomain(null, "Campinas", uf2);
		
		uf1.getCidades().addAll(Arrays.asList(cid1));
		uf2.getCidades().addAll(Arrays.asList(cid2,cid3));
		
		repoEstado.saveAll(Arrays.asList(uf1, uf2));
		repoCidade.saveAll(Arrays.asList(cid1, cid2, cid3));
		
		ClienteDomain cli1 = new ClienteDomain(null, "Maria Silva", "maria@gmail.com", "36378912377", enumTipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		EnderecoDomain e1 = new EnderecoDomain(null, "Rua Flores", "300", "Ap 303", "Jardins", "38220830", cli1, cid1);
		EnderecoDomain e2 = new EnderecoDomain(null, "Av. Matos", "105", "Sala 800", "Centro", "38777012", cli1, cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		repoCliente.saveAll(Arrays.asList(cli1));
		repoEndereco.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		PedidoDomain ped1 = new PedidoDomain(null, simpleDateFormat.parse("30/09/2017 10:32"),cli1, e1);
		PedidoDomain ped2 = new PedidoDomain(null, simpleDateFormat.parse("10/10/2017 19:35"),cli1, e2);
		
		PagamentoDomain pagto1 = new PagamentoComCartaoDomain(null, enumEstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		PagamentoDomain pagto2 = new PagamentoComBoletoDomain(null, enumEstadoPagamento.PENDENTE, ped2, simpleDateFormat.parse("20/10/2017 00:99"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		repoPedido.saveAll(Arrays.asList(ped1,ped2));
		repoPagamento.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedidoDomain itemPed1 = new ItemPedidoDomain(ped1, prod1, 0.00, 1, 2000.00);
		ItemPedidoDomain itemPed2 = new ItemPedidoDomain(ped1, prod3, 0.00, 2, 80.00);
		ItemPedidoDomain itemPed3 = new ItemPedidoDomain(ped2, prod2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(itemPed1,itemPed2));
		ped2.getItens().addAll(Arrays.asList(itemPed3));
		
		prod1.getItens().addAll(Arrays.asList(itemPed1));
		prod2.getItens().addAll(Arrays.asList(itemPed3));
		prod3.getItens().addAll(Arrays.asList(itemPed2));
		
		repoItemPedido.saveAll(Arrays.asList(itemPed1,itemPed2,itemPed3));

	}

}
