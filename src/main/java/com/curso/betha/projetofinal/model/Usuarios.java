package com.curso.betha.projetofinal.model;

import com.curso.betha.projetofinal.dao.UsuariosDAO;
import com.curso.betha.projetofinal.utils.Parseable;
import com.curso.betha.projetofinal.utils.Utils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by NatanRamos on 29/09/2016.
 */
public class Usuarios implements Parseable {

    private Long id;
    private String nome;
    private Date dataCadastro;
    private String email;
    private String login;
    private String senha;

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

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return String.format("{\"id\":\"%s\",\"nome\":\"%s\",\"dataCadastro\":\"%s\",\"email\":\"%s\",\"login\":\"%s\",\"senha\":\"%s\"}", this.id, this.nome, this.dataCadastro, this.email, this.login, this.senha);
    }

    @Override
    public void parse(Map<String, String> dados) {
        this.id = dados.get("id") == null || dados.get("id").isEmpty() ? null : Long.parseLong(dados.get("id"));
        this.nome = dados.get("nome");
        this.dataCadastro = Utils.parseDate(dados.get("dataCadastro"));
        this.email = dados.get("email");
        this.login = dados.get("login");
        this.senha = dados.get("senha");
    }

    /*public void salvar() throws Exception{
        if(this.getId() == null) {
            UsuariosDAO.salvar(this);
        } else {
            UsuariosDAO.atualizar(this);
        }
    }

    public void excluir() {
        UsuariosDAO.excluir(this.getId());
    }

    public static Usuarios getUsuario(Long codigo) {
        return UsuariosDAO.getUsuario(codigo);
    }

    public static List<Usuarios> getUsuarios() {
        return UsuariosDAO.getUsuarios();
    }

    public Long realizarLogin(String login, String senha){
        return UsuariosDAO.realizarLogin(login, senha);
    }*/
}
