package com.curso.betha.projetofinal.model;

/**
 * Created by NatanRamos on 29/09/2016.
 */
public class Municipios {

    private Long id;
    private Long idEstados;
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEstados() {
        return idEstados;
    }

    public void setIdEstados(Long idEstados) {
        this.idEstados = idEstados;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
