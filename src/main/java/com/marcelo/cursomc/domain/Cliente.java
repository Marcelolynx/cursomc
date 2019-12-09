package com.marcelo.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    public Cliente() {
    }

    public Cliente(Integer id, String nome, String email, String cpfouCnpj, TipoCliente tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfouCnpj = cpfouCnpj;
        this.tipo = tipo;
    }

    private Integer id;
    private String nome;
    private String email;
    private String cpfouCnpj;
    private TipoCliente tipo;

    private List<Endereco> enderecos = new ArrayList<>();


    private Set<String> telefones = new HashSet<>();

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfouCnpj() {
        return cpfouCnpj;
    }

    public void setCpfouCnpj(String cpfouCnpj) {
        this.cpfouCnpj = cpfouCnpj;
    }

    public TipoCliente getTipo() {
        return tipo;
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }
}