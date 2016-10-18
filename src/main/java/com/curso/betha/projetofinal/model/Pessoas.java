package com.curso.betha.projetofinal.model;

import com.curso.betha.projetofinal.utils.Parseable;
import com.curso.betha.projetofinal.utils.Utils;

import java.util.Date;
import java.util.Map;

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
    private Long idMunicipios;
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

    public Long getIdMunicipios() {
        return idMunicipios;
    }

    public void setIdMunicipios(Long idMunicipios) {
        this.idMunicipios = idMunicipios;
    }

    public Long getIdEstados() {
        return idEstados;
    }

    public void setIdEstados(Long idEstados) {
        this.idEstados = idEstados;
    }

    @Override
    public String toString() {
        return String.format("{\"id\":\"%s\",\"nome\":\"%s\",\"tipoPessoa\":\"%s\",\"documento\":\"%s\",\"dataNascimento\":\"%s\",\"telefone\":\"%s\",\"email\":\"%s\",\"rua\":\"%s\",\"numero\":\"%s\",\"idMunicipios\":\"%s\",\"idEstados\":\"%s\"}", this.id, this.nome, this.tipoPessoa, this.documento, this.dataNascimento, this.telefone, this.email, this.rua, this.numero, this.idMunicipios, this.idEstados);
    }

    @Override
    public void parse(Map<String, String> dados) {
        this.id = dados.get("id") == null || dados.get("id").isEmpty() ? null : Long.parseLong(dados.get("id"));
        this.nome = dados.get("nome");
        this.tipoPessoa = dados.get("tipoPessoa");
        this.documento = dados.get("documento");
        this.dataNascimento = Utils.parseDate(dados.get("dataNascimento"));
        this.telefone = dados.get("telefone");
        this.email = dados.get("email");
        this.rua = dados.get("rua");
        this.numero = dados.get("numero");
        this.idMunicipios = Utils.parseLong(dados.get("idMunicipios"));
        this.idEstados = Utils.parseLong(dados.get("idEstados"));
    }
}
