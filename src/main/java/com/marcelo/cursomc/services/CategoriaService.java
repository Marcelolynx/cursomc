package com.marcelo.cursomc.services;

import com.marcelo.cursomc.domain.Categoria;
import com.marcelo.cursomc.exceptions.ObjectNotFoundException;
import com.marcelo.cursomc.repositories.CategoriaRepository;
import com.marcelo.cursomc.services.exceptions.DataIntegretyException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Optional<Categoria> find(Integer id) {

        Optional<Categoria> obj = categoriaRepository.findById(id);
        if (obj == null) {
        	throw new ObjectNotFoundException("Objeto não encontrado! " + id 
        			+ "Tipo: " + Categoria.class.getName());
        }
        return obj;

    }
    
    
    public Categoria insert (Categoria obj) {
    	obj.setId(null);
    	return categoriaRepository.save(obj);
    }
    
    public Categoria update(Categoria obj) {
    	find(obj.getId());
    	if(obj.getId() == null) {
    		throw new ObjectNotFoundException("Objeto não encontrado! " + "Tipo: " + Categoria.class.getName());
    	}
    	return categoriaRepository.save(obj);
    }
    
    public void delete(Integer id) {
    	find(id);
    	try {
    	categoriaRepository.deleteById(id);
    
    	}
    	catch (DataIntegrityViolationException e) {
    		throw new DataIntegretyException("Não é possível deletar uma Categoria que tenha produtos relacionados");
    	}
   }

}
