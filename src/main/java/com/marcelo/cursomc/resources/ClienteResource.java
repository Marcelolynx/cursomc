package com.marcelo.cursomc.resources;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.marcelo.cursomc.domain.Categoria;
import com.marcelo.cursomc.dto.CategoriaDTO;
import com.marcelo.cursomc.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.marcelo.cursomc.domain.Cliente;
import com.marcelo.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findId(@PathVariable Integer id) {

        Optional<Cliente> obj = clienteService.find(id);

        return ResponseEntity.ok().body(obj);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        clienteService.delete(id);

        return ResponseEntity.noContent().build();

    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {

        List<Cliente>  list = clienteService.findAll();
        List<ClienteDTO>
                listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value="/page",method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value="page", defaultValue="0")Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="nome")String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) {

        Page<Cliente>  list = clienteService.findPage(page, linesPerPage, orderBy, direction);
        Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));

        return ResponseEntity.ok().body(listDto);
    }


}
