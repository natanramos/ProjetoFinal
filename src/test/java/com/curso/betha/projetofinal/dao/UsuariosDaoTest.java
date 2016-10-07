package com.curso.betha.projetofinal.dao;

import com.curso.betha.projetofinal.model.Usuarios;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by NatanRamos on 07/10/2016.
 */
public class UsuariosDaoTest {

    private final UsuariosDAO dao = new UsuariosDAO();

    @Test
    public void testInsertSemEmailComDelete() {
        Usuarios usuario = new Usuarios();
        usuario.setNome("Pedro de souza");
        usuario.setLogin("pedro.souza");
        usuario.setSenha("betha123");

        usuario = dao.inserir(usuario);
        Long idAdicionado = usuario.getId();

        Assert.assertNotNull(usuario.getId());
        Assert.assertNull(usuario.getEmail());

        dao.excluir(idAdicionado);

        Assert.assertNull(dao.getUsuario(idAdicionado));
    }
}
