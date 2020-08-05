package com.fellipedias.springtests;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fellipedias.springtests.domain.Categoria;
import com.fellipedias.springtests.domain.Cidade;
import com.fellipedias.springtests.domain.Cliente;
import com.fellipedias.springtests.domain.Endereco;
import com.fellipedias.springtests.domain.Estado;
import com.fellipedias.springtests.domain.Produto;
import com.fellipedias.springtests.domain.enums.TipoCliente;
import com.fellipedias.springtests.repositories.CategoriaRepository;
import com.fellipedias.springtests.repositories.CidadeRepository;
import com.fellipedias.springtests.repositories.ClienteRepository;
import com.fellipedias.springtests.repositories.EnderecoRepository;
import com.fellipedias.springtests.repositories.EstadoRepository;
import com.fellipedias.springtests.repositories.ProdutoRepository;

@SpringBootApplication
public class SpringTestsApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository  cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringTestsApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
						
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategoria().addAll(Arrays.asList(cat1));
		p2.getCategoria().addAll(Arrays.asList(cat1,cat2));
		p3.getCategoria().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlândia",est1);
		Cidade c2 = new Cidade(null,"Campinas",est2);
		Cidade c3 = new Cidade(null,"São Paulo",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		Cliente cli1 = new Cliente(null,"Roberto Silva","RobSil@gmail.com",TipoCliente.PESSOAFISICA,"31256478971");
				
		Endereco end1 = new Endereco(null,"Rua A","300","Apto 303","Jardim","35216548",cli1,c1);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1));
			
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1));
	}

}
