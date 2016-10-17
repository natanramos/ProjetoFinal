package com.curso.betha.projetofinal.model;

import com.curso.betha.projetofinal.utils.Parseable;
import com.curso.betha.projetofinal.utils.Utils;

import java.util.Map;

/**
 * Created by NatanRamos on 29/09/2016.
 */
public class Municipios implements Parseable {

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

    @Override
    public String toString() {
        return String.format("{\"id\":\"%s\",\"idEstados\":\"%s\",\"nome\":\"%s\"}", this.id, this.idEstados, this.nome);
    }

    @Override
    public void parse(Map<String, String> dados) {
        this.id = dados.get("id") == null || dados.get("id").isEmpty() ? null : Long.parseLong(dados.get("id"));
        this.idEstados = Utils.parseLong(dados.get("idEstados"));
        this.nome = dados.get("nome");
    }
}
