package com.curso.betha.projetofinal.servlets;

import com.curso.betha.projetofinal.dao.CompetenciasDAO;
import com.curso.betha.projetofinal.model.Competencias;
import com.curso.betha.projetofinal.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by NatanRamos on 13/10/2016.
 */

@WebServlet(urlPatterns = {"api/competencias", "api/competencias/remover"})
public class CompetenciasServlet extends HttpServlet {

    private final CompetenciasDAO dao = new CompetenciasDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Utils.isNotEmpty(req.getParameter("id"))) {
            Competencias competencia = dao.getCompetencia(Utils.parseLong(req.getParameter("id")));
            if (competencia == null)
                resp.sendError(404);
            else {
                resp.setContentType("application/json");
                resp.getWriter().append(competencia.toString());
            }
        } else {
            resp.setContentType("application/json");
            resp.getWriter().append(dao.getCompetencias().toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Competencias competencia = new Competencias();
        competencia.parse(Utils.parseMap(req));
        if (Utils.isNotEmpty(req.getParameter("id"))) {
            dao.atualizar(competencia);
        } else {
            dao.inserir(competencia);
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
