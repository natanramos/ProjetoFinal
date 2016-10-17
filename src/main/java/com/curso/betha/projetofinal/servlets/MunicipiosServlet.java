package com.curso.betha.projetofinal.servlets;

import com.curso.betha.projetofinal.dao.MunicipiosDAO;
import com.curso.betha.projetofinal.model.Municipios;
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

@WebServlet(urlPatterns = {"api/municipios", "api/municipios/remover"})
public class MunicipiosServlet extends HttpServlet {

    private final MunicipiosDAO dao = new MunicipiosDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Utils.isNotEmpty(req.getParameter("id"))) {
            Municipios municipio = dao.getMunicipio(Utils.parseLong(req.getParameter("id")));
            if (municipio == null)
                resp.sendError(404);
            else {
                resp.setContentType("application/json");
                resp.getWriter().append(municipio.toString());
            }
        } else {
            resp.setContentType("application/json");
            resp.getWriter().append(dao.getMunicipios().toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Municipios municipio = new Municipios();
        municipio.parse(Utils.parseMap(req));
        if (Utils.isNotEmpty(req.getParameter("id"))) {
            dao.atualizar(municipio);
        } else {
            dao.inserir(municipio);
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
