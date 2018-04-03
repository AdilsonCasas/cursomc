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
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.repositories.EstadoRepository;
import com.nelioalves.cursomc.repositories.ItemPedidoRepository;
import com.nelioalves.cursomc.repositories.PagamentoRepository;
import com.nelioalves.cursomc.repositories.PedidoRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepo;
	@Autowired
	private ProdutoRepository produtoRepo;
	@Autowired
	private EstadoRepository estadoRepo;
	@Autowired
	private CidadeRepository cidadeRepo;
	@Autowired
	private ClienteRepository clienteRepo;
	@Autowired
	private EnderecoRepository enderecoRepo;
	@Autowired
	private PedidoRepository pedidoRepo;
	@Autowired
	private PagamentoRepository pagamentoRepo;
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Categoria+Produto
		domainCategoria cat1 = new domainCategoria(null, "Informática");
		domainCategoria cat2 = new domainCategoria(null, "Escritório");
		
		domainProduto p1 = new domainProduto(null, "Computador", 2000.00);
		domainProduto p2 = new domainProduto(null, "Impressora",  800.00);
		domainProduto p3 = new domainProduto(null, "Mouse",        80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepo.saveAll(Arrays.asList(cat1, cat2));
		produtoRepo.saveAll(Arrays.asList(p1, p2, p3));

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
		
		domainItemPedido ip1 = new domainItemPedido(ped1, p1, 0.00, 1, 2000.00);
		domainItemPedido ip2 = new domainItemPedido(ped1, p3, 0.00, 2, 80.00);
		domainItemPedido ip3 = new domainItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepo.saveAll(Arrays.asList(ip1,ip2,ip3));
	}
}
