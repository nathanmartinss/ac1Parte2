package com.example.ac1part2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import com.example.ac1part2.models.Categoria;
import com.example.ac1part2.models.Produto;
import com.example.ac1part2.repositories.CategoriaRepository;
import com.example.ac1part2.repositories.ProdutoRepository;

import java.util.List;
import java.util.ArrayList;

@SpringBootApplication
public class Ac1part2Application {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(Ac1part2Application.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return new CommandLineRunner() {
			@Override
			@Transactional
			public void run(String... args) throws Exception {
				// Criando categorias
				List<Produto> produtos = new ArrayList<>();
				Categoria categoria1 = new Categoria(null, "Jogos", produtos);
				Categoria categoria2 = new Categoria(null, "Eletronicos");
				categoria1 = categoriaRepository.save(categoria1);
				categoria2 = categoriaRepository.save(categoria2);

				// Criando produtos
				Produto produto1 = new Produto(null, "The Last Of Us Part II", 350.00, categoria1);
				Produto produto2 = new Produto(null, "Uncharted", 250.00, categoria1);
				Produto produto3 = new Produto(null, "Aspirador de Pó", 50.00, categoria1);
				produtoRepository.saveAll(List.of(produto1, produto2, produto3));
				categoria1.setProdutos(produtoRepository.findAll());

				// Consultas
				List<Produto> produtosPrecoMaiorQue = produtoRepository.findByPrecoGreaterThan(300.00);
				List<Produto> produtosPrecoMenorOuIgual = produtoRepository.findByPrecoLessThanEqual(299.00);
				List<Produto> produtosNomeComecaCom = produtoRepository.findByNomeStartingWith("T");

				System.out.println("Produtos com preço maior que 300.00:");
				//produtosPrecoMaiorQue.forEach(System.out::println);

				System.out.println("Produtos com preço menor ou igual a 299.00:");
				//produtosPrecoMenorOuIgual.forEach(System.out::println);

				System.out.println("Produtos cujo nome começa com 'T':");
				//produtosNomeComecaCom.forEach(System.out::println);

				// Consultas de Categoria
				List<Categoria> categoriasNomeComecaCom = categoriaRepository.findByNomeStartingWith("E");
				Categoria categoriaComProdutos = categoriaRepository.findByIdWithProdutos(1L);
				System.out.println("Teste Ronaldo");

				System.out.println("Categorias cujo nome começa com 'E':");
				//categoriasNomeComecaCom.forEach(System.out::println);

				if (categoriaComProdutos != null) {
					System.out.println("Categoria com ID 1 e seus produtos:");

					categoriaComProdutos.getProdutos().stream().map(p -> p.getNome());
				}
			}
		};
	}
}
