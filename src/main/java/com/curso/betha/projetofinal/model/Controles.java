package com.curso.betha.projetofinal.model;

import com.curso.betha.projetofinal.utils.Parseable;
import com.curso.betha.projetofinal.utils.Utils;

import java.util.Date;
import java.util.Map;

/**
 * Created by NatanRamos on 30/09/2016.
 */
public class Controles implements Parseable {

    private Long id;
    private String mensalista;
    private Long idPessoas;
    private String placa;
    private String marca;
    private String modelo;
    private String cor;
    private String responsavel;
    private Date dataHoraEntrada;
    private Date dataHoraSaida;
    private String situacao;
    private Double valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensalista() {
        return mensalista;
    }

    public void setMensalista(String mensalista) {
        this.mensalista = mensalista;
    }

    public Long getIdPessoas() {
        return idPessoas;
    }

    public void setIdPessoas(Long idPessoas) {
        this.idPessoas = idPessoas;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public Date getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public void setDataHoraEntrada(Date dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public Date getDataHoraSaida() {
        return dataHoraSaida;
    }

    public void setDataHoraSaida(Date dataHoraSaida) {
        this.dataHoraSaida = dataHoraSaida;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return String.format("{\"id\":\"%s\",\"mensalista\":\"%s\",\"idPessoas\":\"%s\",\"placa\":\"%s\",\"marca\":\"%s\",\"modelo\":\"%s\",\"cor\":\"%s\",\"responsavel\":\"%s\",\"dataHoraEntrada\":\"%s\",\"dataHoraSaida\":\"%s\",\"situacao\":\"%s\",\"valor\":\"%s\"}",
                this.id, this.mensalista, this.idPessoas, this.placa, this.marca, this.modelo, this.cor, this.responsavel, this.dataHoraEntrada, this.dataHoraSaida, this.situacao, this.valor);
    }

    @Override
    public void parse(Map<String, String> dados) {
        this.id = dados.get("id") == null || dados.get("id").isEmpty() ? null : Long.parseLong(dados.get("id"));
        this.mensalista = dados.get("mensalista");
        this.idPessoas = Utils.parseLong(dados.get("idPessoas"));
        this.placa = dados.get("placa");
        this.marca = dados.get("marca");
        this.modelo = dados.get("modelo");
        this.cor = dados.get("cor");
        this.responsavel = dados.get("responsavel");
        this.dataHoraEntrada = Utils.parseDateTime(dados.get("dataHoraEntrada"));
        this.dataHoraSaida = Utils.parseDateTime(dados.get("dataHoraSaida"));
        this.situacao = dados.get("situacao");
        this.valor = Utils.parseDouble(dados.get("valor"));
    }
}
