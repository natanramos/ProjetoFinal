package com.curso.betha.projetofinal.servlets;

import com.curso.betha.projetofinal.dao.UsuariosDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by NatanRamos on 12/10/2016.
 */

@WebServlet(urlPatterns = {"api/utilitarios/login", "api/utilitarios/dataAtual"})
public class UtilitariosServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().endsWith("/dataAtual") && "GET".equals(req.getMethod())) {
            resp.getWriter().append(dataAtual());
        } else if (req.getRequestURI().endsWith("/login") && "POST".equals(req.getMethod())) {
            String usuario = req.getParameter("usuario");
            String senha = req.getParameter("senha");
            if (usuario != null && senha != null) {
                Long retorno = UsuariosDAO.realizarLogin(usuario, senha);
                resp.getWriter().append(String.valueOf(retorno));
            } else {
                resp.getWriter().append(String.valueOf(0));
            }
        }
    }

    public String dataAtual(){
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        return formato.format(new Date());
    }
}
