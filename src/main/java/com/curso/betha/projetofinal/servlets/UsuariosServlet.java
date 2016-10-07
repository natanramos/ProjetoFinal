package com.curso.betha.projetofinal.servlets;

import com.curso.betha.projetofinal.dao.UsuariosDAO;
import com.curso.betha.projetofinal.model.Usuarios;
import com.curso.betha.projetofinal.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by NatanRamos on 06/10/2016.
 */

@WebServlet("/api/usuarios")
public class UsuariosServlet extends HttpServlet {

    private final UsuariosDAO dao = new UsuariosDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Utils.isNotEmpty(req.getParameter("id"))) {
            Usuarios usuario = dao.getUsuario(Utils.parseLong(req.getParameter("id")));
            if (usuario == null)
                resp.sendError(404);
            else {
                resp.setContentType("application/json");
                resp.getWriter().append(usuario.toString());
            }
        } else {
            resp.setContentType("application/json");
            resp.getWriter().append(dao.getUsuarios().toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
