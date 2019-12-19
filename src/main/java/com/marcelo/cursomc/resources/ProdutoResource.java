package com.marcelo.cursomc.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.cursomc.domain.Produto;
import com.marcelo.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	   public ResponseEntity<?> toList(@PathVariable Integer id) {
		   
		 Optional<Produto> obj =  produtoService.find(id);
		   
		   return ResponseEntity.ok().body(obj);
	   }

}