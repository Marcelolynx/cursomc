package com.marcelo.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marcelo.cursomc.domain.Categoria;
import com.marcelo.cursomc.domain.Produto;
import com.marcelo.cursomc.repositories.CategoriaRepository;
import com.marcelo.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
	    Categoria cat1 = new Categoria(null, "Sedan");
	    Categoria cat2 = new Categoria(null, "Hatch");
    
	    
	    Produto p1 = new Produto(null, "Jetta", 128000.00);
	    Produto p2 = new Produto(null, "Golf", 112000.00);
	    Produto p3 = new Produto(null, "Polo", 78000.00);
	    
	    
	    cat1.getProdutos().addAll(Arrays.asList(p1));
	    cat2.getProdutos().addAll(Arrays.asList(p2, p3));
	    
	    
	    p1.getCategorias().addAll(Arrays.asList(cat1));
	    p2.getCategorias().addAll(Arrays.asList(cat2));
	    p3.getCategorias().addAll(Arrays.asList(cat2));
	    
	    
    
    categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
    produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
    
    
    }
}
