package com.marcelo.cursomc.services;

import com.marcelo.cursomc.domain.Categoria;
import com.marcelo.cursomc.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    
    public Optional<Categoria> search(Integer id) {
    	
    	Optional<Categoria> obj = categoriaRepository.findById(id);
    	
    	return obj;
    	
    }
 
}
