package com.curso.betha.projetofinal.dao;

import com.curso.betha.projetofinal.model.Usuarios;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Created by NatanRamos on 07/10/2016.
 */
public class UsuariosDaoTest {

    private final UsuariosDAO dao = new UsuariosDAO();
    private final Long ID = 1L;
    private final String NOME = "Pedro de Souza";
    private final Date DATA_CADASTRO = new Date();
    private final String EMAIL = "pedro.souza@gmail.com";
    private final String LOGIN = "pedro.souza";
    private final String SENHA = "betha123";

    public Usuarios buildUsuarioCompleto(){
        Usuarios usuario = new Usuarios();
        usuario.setId(ID);
        usuario.setNome(NOME);
        usuario.setDataCadastro(DATA_CADASTRO);
        usuario.setEmail(EMAIL);
        usuario.setLogin(LOGIN);
        usuario.setSenha(SENHA);
        return usuario;
    }

    public Usuarios buildUsuarioSemID(){
        Usuarios usuario = new Usuarios();
        usuario.setNome(NOME);
        usuario.setDataCadastro(DATA_CADASTRO);
        usuario.setEmail(EMAIL);
        usuario.setLogin(LOGIN);
        usuario.setSenha(SENHA);
        return usuario;
    }

    @Test
    public void testBuildCompleto(){
        Usuarios usuario = buildUsuarioCompleto();

        Assert.assertNotNull(usuario.getId());
        Assert.assertNotNull(usuario.getNome());
        Assert.assertNotNull(usuario.getDataCadastro());
        Assert.assertNotNull(usuario.getEmail());
        Assert.assertNotNull(usuario.getLogin());
        Assert.assertNotNull(usuario.getSenha());

        Assert.assertEquals(usuario.getId(), ID);
        Assert.assertEquals(usuario.getNome(), NOME);
        Assert.assertEquals(usuario.getDataCadastro(), DATA_CADASTRO);
        Assert.assertEquals(usuario.getEmail(), EMAIL);
        Assert.assertEquals(usuario.getLogin(), LOGIN);
        Assert.assertEquals(usuario.getSenha(), SENHA);

    }

    @Test
    public void testInsertSemEmailComDelete() {
        Usuarios usuario = buildUsuarioSemID();
        usuario.setEmail(null);

        usuario = dao.inserir(usuario);
        Long idAdicionado = usuario.getId();

        Assert.assertNotNull(usuario.getId());
        Assert.assertNull(usuario.getEmail());

        dao.excluir(idAdicionado);

        Assert.assertNull(dao.getUsuario(idAdicionado));
    }
}
