package com.marcelo.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelo.cursomc.domain.Produto;
import com.marcelo.cursomc.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Optional<Produto> search(Integer id) {
    	
    	Optional<Produto> obj = produtoRepository.findById(id);
    	
    	return obj;
    	
    }

}
