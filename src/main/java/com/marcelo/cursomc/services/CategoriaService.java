package com.marcelo.cursomc.services;

import com.marcelo.cursomc.domain.Categoria;
import com.marcelo.cursomc.dto.CategoriaDTO;
import com.marcelo.cursomc.exceptions.ObjectNotFoundException;
import com.marcelo.cursomc.repositories.CategoriaRepository;
import com.marcelo.cursomc.services.exceptions.DataIntegretyException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id) {

        Optional<Categoria> obj = categoriaRepository.findById(id);
         
         return obj.orElseThrow(() -> new ObjectNotFoundException(
        		 "Objeto não encontrado!  id: " + id + ", Tipo: " + Categoria.class.getName()));
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
    
    public List<Categoria> findAll() {
    	return categoriaRepository.findAll();
    	
    }
    
    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
    	PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest);
    }
    
    public Categoria fromDTO(CategoriaDTO objDto) {

    	return new Categoria(objDto.getId(), objDto.getNome());
    }

}
