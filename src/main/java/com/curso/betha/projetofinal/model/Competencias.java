package com.curso.betha.projetofinal.model;

import com.curso.betha.projetofinal.utils.Parseable;
import com.curso.betha.projetofinal.utils.Utils;

import java.util.Date;
import java.util.Map;

/**
 * Created by NatanRamos on 30/09/2016.
 */
public class Competencias implements Parseable {

    private Long id;
    private String descricao;
    private Date dataInicial;
    private Date dataFinal;
    private Date dataVencimento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    @Override
    public String toString() {
        return String.format("{\"id\":\"%s\",\"descricao\":\"%s\",\"dataInicial\":\"%s\",\"dataFinal\":\"%s\",\"dataVencimento\":\"%s\"}", this.id, this.descricao, this.dataInicial, this.dataFinal, this.dataVencimento);
    }

    @Override
    public void parse(Map<String, String> dados) {
        this.id = dados.get("id") == null || dados.get("id").isEmpty() ? null : Long.parseLong(dados.get("id"));
        this.descricao = dados.get("descricao");
        this.dataInicial = Utils.parseDate(dados.get("dataInicial"));
        this.dataFinal = Utils.parseDate(dados.get("dataFinal"));
        this.dataVencimento = Utils.parseDate(dados.get("dataVencimento"));
    }
}
