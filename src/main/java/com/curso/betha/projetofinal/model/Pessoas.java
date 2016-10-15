package com.curso.betha.projetofinal.model;

import com.curso.betha.projetofinal.utils.Parseable;

import java.util.Date;

/**
 * Created by NatanRamos on 29/09/2016.
 */
public class Pessoas implements Parseable {

    private Long id;
    private String nome;
    private String tipoPessoa;
    private String documento;
    private Date dataNascimento;
    private String telefone;
    private String email;
    private String rua;
    private String numero;
    private Long idMunicipio;
    private Long idEstados;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Long getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Long idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Long getIdEstados() {
        return idEstados;
    }

    public void setIdEstados(Long idEstados) {
        this.idEstados = idEstados;
    }

    @Override
    public String toString() {
        return String.format("{\"id\":\"%s\",\"descricao\":\"%s\",\"dataInicial\":\"%s\",\"dataFinal\":\"%s\",\"dataVencimento\":\"%s\"}", this.id, this.descricao, this.dataInicial, this.dataFinal, this.dataVencimento);
    }
}
