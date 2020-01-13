package com.marcelo.cursomc.dto;

import com.marcelo.cursomc.domain.Produto;

import java.io.Serializable;

public class ProdutoDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;
    private String nome;
    private double valor;


    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto obj) {
        id = obj.getId();
        nome = obj.getNome();
        valor = obj.getValor();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
