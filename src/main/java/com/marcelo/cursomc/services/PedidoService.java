package com.marcelo.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.marcelo.cursomc.domain.Pedido; 
import com.marcelo.cursomc.repositories.PedidoRepository;
import com.marcelo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Optional<Pedido> find(Integer id) {
    	
        Optional<Pedido> obj = pedidoRepository.findById(id);
        	if (obj == null) {
        		throw new ObjectNotFoundException("Objeto não encontrado! Id: "
        				+ id + ", Tipo: " + Pedido.class.getName());
	    	}
        	
        	return obj;

    }

}
