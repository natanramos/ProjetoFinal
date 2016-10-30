package com.curso.betha.projetofinal.servlets;

import com.curso.betha.projetofinal.dao.ControlesDAO;
import com.curso.betha.projetofinal.model.Controles;
import com.curso.betha.projetofinal.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by NatanRamos on 25/10/2016.
 */

@WebServlet(urlPatterns = {"api/controles", "api/controles/remover", "api/controles/encerrar", "api/controles/pendente"})
public class ControlesServlet extends HttpServlet {

    private final ControlesDAO dao = new ControlesDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Utils.isNotEmpty(req.getParameter("id"))) {
            Controles controle = dao.getControle(Utils.parseLong(req.getParameter("id")));
            if (controle == null)
                resp.sendError(404);
            else {
                resp.setContentType("application/json");
                resp.getWriter().append(controle.toString());
            }
        }else if (Utils.isNotEmpty(req.getParameter("situacao"))) {
            resp.setContentType("application/json");
            resp.getWriter().append(dao.getControles(req.getParameter("situacao")).toString());
        } else {
            resp.setContentType("application/json");
            resp.getWriter().append(dao.getControles().toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Controles controle = new Controles();
        controle.parse(Utils.parseMap(req));
        if (Utils.isNotEmpty(req.getParameter("id"))) {
            dao.atualizar(controle);
        } else {
            dao.inserir(controle);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().endsWith("/remover") && "POST".equals(req.getMethod())) {
            dao.excluir(Utils.parseLong(req.getParameter("id")));
        } else if (req.getRequestURI().endsWith("/encerrar") && "POST".equals(req.getMethod())) {
            dao.encerrar(Utils.parseLong(req.getParameter("id")), req.getParameter("situacao"));
        } else if (req.getRequestURI().endsWith("/pendente") && "POST".equals(req.getMethod())) {
            dao.pendente(Utils.parseLong(req.getParameter("id")));
        } else {
            super.service(req, resp);
        }
    }
}
