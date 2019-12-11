package com.marcelo.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.marcelo.cursomc.domain.Cliente;
import com.marcelo.cursomc.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	 public Optional<Cliente> search(Integer id) {
	    	
	    	Optional<Cliente> obj = clienteRepository.findById(id);
	    	  	return obj;
	    	
	    }

}
