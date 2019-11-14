package com.marcelo.cursomc.services;

import com.marcelo.cursomc.domain.Categoria;
import com.marcelo.cursomc.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Optional<Categoria> buscar(Integer id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);

        return obj;
    }

    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> find(Integer id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);

        return obj;
    }

    public Categoria salvar(Categoria categoria) {
         return categoriaRepository.save(categoria);
    }

    public void   deletar(Integer id) {
        find(id);

        categoriaRepository.deleteById(id);

    }
}
