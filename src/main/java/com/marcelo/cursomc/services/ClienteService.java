package com.marcelo.cursomc.services;

import java.util.List;
import java.util.Optional;

import com.marcelo.cursomc.domain.*;
import com.marcelo.cursomc.dto.ClienteDTO;
import com.marcelo.cursomc.dto.ClienteNewDTO;
import com.marcelo.cursomc.exceptions.ObjectNotFoundException;
import com.marcelo.cursomc.repositories.EnderecoRepository;
import com.marcelo.cursomc.services.exceptions.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.marcelo.cursomc.repositories.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Optional<Cliente> find(Integer id) {

        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj;

    }

    public Cliente insert (Cliente obj) {
        obj.setId(null);
        obj = clienteRepository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }


    public Cliente update(Cliente obj) {
        find(obj.getId());
        if(obj.getId() == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! " + "Tipo: " + Cliente.class.getName());
        }
        return clienteRepository.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try { clienteRepository.deleteById(id);

        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível deletar uma Cliente que tenha atendimentos relacionados");
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

    public Cliente fromDTO(ClienteNewDTO objDto) {
        Cidade cidade = new Cidade(objDto.getCidadeId(), null, null);
        Cliente cliente = new Cliente(
                null, objDto.getNome(),
                objDto.getEmail(),
                objDto.getCpfouCnpj(),
                TipoCliente.toEnum(objDto.getTipo()));
        Endereco endereco = new Endereco(
                null,
                objDto.getLogradouro(),
                objDto.getNumero(),
                objDto.getComplemento(),
                objDto.getBairro(),
                objDto.getCep(), cliente, cidade );
        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(objDto.getTelefone1());
        if(objDto.getTelefone2() != null) {
            cliente.getTelefones().add(objDto.getTelefone2());
        }
        if(objDto.getTelefone3() != null) {
            cliente.getTelefones().add(objDto.getTelefone3());
        }
        return cliente;
    }
}