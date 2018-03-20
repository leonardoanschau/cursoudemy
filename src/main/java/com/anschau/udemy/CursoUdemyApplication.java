package com.anschau.udemy;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.anschau.udemy.domain.Categoria;
import com.anschau.udemy.domain.Cidade;
import com.anschau.udemy.domain.Cliente;
import com.anschau.udemy.domain.Endereco;
import com.anschau.udemy.domain.Estado;
import com.anschau.udemy.domain.ItemPedido;
import com.anschau.udemy.domain.Pagamento;
import com.anschau.udemy.domain.PagamentoComBoleto;
import com.anschau.udemy.domain.PagamentoComCartao;
import com.anschau.udemy.domain.Pedido;
import com.anschau.udemy.domain.Produto;
import com.anschau.udemy.domain.enums.EstadoPagamento;
import com.anschau.udemy.domain.enums.TipoCliente;
import com.anschau.udemy.repositories.CategoriaRepository;
import com.anschau.udemy.repositories.CidadeRepository;
import com.anschau.udemy.repositories.ClienteRepository;
import com.anschau.udemy.repositories.EnderecoRepository;
import com.anschau.udemy.repositories.EstadoRepository;
import com.anschau.udemy.repositories.ItemPedidoRepository;
import com.anschau.udemy.repositories.PagamentoRepository;
import com.anschau.udemy.repositories.PedidoRepository;
import com.anschau.udemy.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoUdemyApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository cr;
	@Autowired
	private ProdutoRepository pr;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired 
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoUdemyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().add(p2);
		
		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().add(cat1);
		
		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null, "São Paulo");
		
		Cidade cidade1 = new Cidade(null, "Uberlândia", estado1);
		Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado2);
		
		Cliente cliente1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cliente1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco endereco1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38", cliente1, cidade1);
		Endereco endereco2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cliente1, cidade2);
		
		cr.saveAll(Arrays.asList(cat1, cat2));
		pr.saveAll(Arrays.asList(p1, p2, p3));
		
		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
		
		clienteRepository.save(cliente1);
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm"); 
		 
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cliente1, endereco1); 
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cliente1, endereco2);    
		cliente1.getPedidos().addAll(Arrays.asList(ped1, ped2)); 
		 
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1); 
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2); 
		 
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2)); 
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(p1, ped1, 0.00, 1, p1.getPreco());
		ItemPedido ip2 = new ItemPedido(p3, ped1, 0.00, 2, p3.getPreco());
		ItemPedido ip3 = new ItemPedido(p2, ped2, 100.00, 1, p2.getPreco());
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().add(ip3);
		
		p1.getItens().add(ip1);
		p2.getItens().add(ip3);
		p3.getItens().add(ip2);
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}
}
