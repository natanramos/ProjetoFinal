package com.curso.betha.projetofinal.servlets;

import com.curso.betha.projetofinal.dao.PessoasDAO;
import com.curso.betha.projetofinal.model.Pessoas;
import com.curso.betha.projetofinal.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by NatanRamos on 16/10/2016.
 */

@WebServlet(urlPatterns = {"api/pessoas", "api/pessoas/remover"})
public class PessoasServlet extends HttpServlet {

    private final PessoasDAO dao = new PessoasDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Utils.isNotEmpty(req.getParameter("id"))) {
            Pessoas pessoa = dao.getPessoa(Utils.parseLong(req.getParameter("id")));
            if (pessoa == null)
                resp.sendError(404);
            else {
                resp.setContentType("application/json");
                resp.getWriter().append(pessoa.toString());
            }
        } else {
            resp.setContentType("application/json");
            resp.getWriter().append(dao.getPessoas().toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Pessoas pessoa = new Pessoas();
        pessoa.parse(Utils.parseMap(req));
        if (Utils.isNotEmpty(req.getParameter("id"))) {
            dao.atualizar(pessoa);
        } else {
            dao.inserir(pessoa);
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
