package com.curso.betha.projetofinal.model;

import com.curso.betha.projetofinal.utils.Parseable;
import com.curso.betha.projetofinal.utils.Utils;

import java.util.Map;

/**
 * Created by NatanRamos on 29/09/2016.
 */
public class Estados implements Parseable {

    private Long id;
    private Integer codigoIbge;
    private String sigla;
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigoIbge() {
        return codigoIbge;
    }

    public void setCodigoIbge(Integer codigoIbge) {
        this.codigoIbge = codigoIbge;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return String.format("{\"id\":\"%s\",\"codigoIbge\":\"%s\",\"sigla\":\"%s\",\"nome\":\"%s\"}", this.id, this.codigoIbge, this.sigla, this.nome);
    }

    @Override
    public void parse(Map<String, String> dados) {
        this.id = dados.get("id") == null || dados.get("id").isEmpty() ? null : Long.parseLong(dados.get("id"));
        this.codigoIbge = Utils.parseInteger(dados.get("codigoIbge"));
        this.sigla = dados.get("sigla");
        this.nome = dados.get("nome");
    }
}
