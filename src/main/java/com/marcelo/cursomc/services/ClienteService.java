package com.marcelo.cursomc.services;

import java.util.List;
import java.util.Optional;

import com.marcelo.cursomc.domain.Categoria;
import com.marcelo.cursomc.dto.CategoriaDTO;
import com.marcelo.cursomc.dto.ClienteDTO;
import com.marcelo.cursomc.exceptions.ObjectNotFoundException;
import com.marcelo.cursomc.services.exceptions.DataIntegretyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.marcelo.cursomc.domain.Cliente;
import com.marcelo.cursomc.repositories.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Optional<Cliente> find(Integer id) {

        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj;

    }

    public Cliente update(Cliente obj) {
        find(obj.getId());
        if(obj.getId() == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! " + "Tipo: " + Categoria.class.getName());
        }
        return clienteRepository.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            clienteRepository.deleteById(id);

        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegretyException("Não é possível deletar uma Categoria que tenha produtos relacionados");
        }
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();

    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO objDto) {

        return new Cliente(
                objDto.getId(),
                objDto.getNome(),
                objDto.getEmail(),
                null,
                null
        );
    }

}


