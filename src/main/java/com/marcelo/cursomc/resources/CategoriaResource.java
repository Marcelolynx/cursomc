package com.marcelo.cursomc.resources;

import com.marcelo.cursomc.domain.Categoria;
import com.marcelo.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {

        Optional<Categoria> obj = categoriaService.buscar(id);


        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {

        List<Categoria> obj =  categoriaService.findAll();

       return ResponseEntity.ok().body(obj);
    }


    @PostMapping
    public ResponseEntity<Categoria> save(@RequestBody Categoria categoria, HttpServletResponse response) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> remove(@PathVariable Integer id) {
        categoriaService.deletar(id);

        return ResponseEntity.noContent().build();
    }


}
