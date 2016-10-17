package com.curso.betha.projetofinal.servlets;

import com.curso.betha.projetofinal.dao.EstadosDAO;
import com.curso.betha.projetofinal.model.Estados;
import com.curso.betha.projetofinal.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by NatanRamos on 17/10/2016.
 */

@WebServlet(urlPatterns = {"api/estados", "api/estados/remover"})
public class EstadosServlet extends HttpServlet {

    private final EstadosDAO dao = new EstadosDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Utils.isNotEmpty(req.getParameter("id"))) {
            Estados estado = dao.getEstado(Utils.parseLong(req.getParameter("id")));
            if (estado == null)
                resp.sendError(404);
            else {
                resp.setContentType("application/json");
                resp.getWriter().append(estado.toString());
            }
        } else {
            resp.setContentType("application/json");
            resp.getWriter().append(dao.getEstados().toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Estados estado = new Estados();
        estado.parse(Utils.parseMap(req));
        if (Utils.isNotEmpty(req.getParameter("id"))) {
            dao.atualizar(estado);
        } else {
            dao.inserir(estado);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().endsWith("/remover") && "POST".equals(req.getMethod())) {
            dao.excluir(Utils.parseLong(req.getParameter("id")));
        } else {
            super.service(req, resp);
        }
    }
}
