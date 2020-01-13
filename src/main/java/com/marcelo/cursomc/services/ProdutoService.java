package com.marcelo.cursomc.services;

import java.util.List;
import java.util.Optional;

import com.marcelo.cursomc.domain.Categoria;
import com.marcelo.cursomc.exceptions.ObjectNotFoundException;
import com.marcelo.cursomc.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.marcelo.cursomc.domain.Produto;
import com.marcelo.cursomc.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Optional<Produto> find(Integer id) {

        Optional<Produto> obj = produtoRepository.findById(id);
        if(obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: "
                    + Produto.class.getName());
        }
        return obj;
    }

    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);

    }

}
