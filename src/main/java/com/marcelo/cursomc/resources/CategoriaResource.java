package com.marcelo.cursomc.resources;


import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.marcelo.cursomc.domain.Categoria;
import com.marcelo.cursomc.services.CategoriaService;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findId(@PathVariable Integer id) {

        Optional<Categoria> obj = categoriaService.search(id);

        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello Java";
    }
    
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> insert(@RequestBody Categoria obj) {
    	obj = categoriaService.insert(obj);
    	URI uri = ServletUriComponentsBuilder
    			.fromCurrentRequest()
    			.path("/{id}")
    			.buildAndExpand(obj.getId())
    			.toUri();
    	
    	return ResponseEntity.created(uri).build();
    }


}
