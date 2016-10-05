package com.curso.betha.projetofinal.servlets;

import com.curso.betha.projetofinal.model.Usuarios;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by NatanRamos on 04/10/2016.
 */
public class AppServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("teste");
        String jsp = null;
        if(request.getRequestURI().endsWith("/login")){
            jsp = login(request, response);
        }else if(request.getRequestURI().endsWith("/sair")){
            //jsp = sair(request, response);
        }else if(request.getRequestURI().endsWith("/novaLista")){
            //jsp = processaListasCompra(request, response);
        }else{
            //jsp = processaListasCompra(request, response);
        }

        if(jsp == null){
            response.sendRedirect(request.getContextPath() + "/listasCompra");
        }else{
            request.getRequestDispatcher(jsp).forward(request, response);
        }
    }

    private String login(HttpServletRequest request, HttpServletResponse response){

        String login = null;
        String senha = null;
        Long usuario = null;

        List resultadoListas = null;

        login = request.getParameter("login");
        senha = request.getParameter("senha");

        Usuarios u = new Usuarios();

        usuario = u.realizarLogin(login, senha);

        if (usuario > 0) {

            u = Usuarios.getUsuario(usuario);

            request.getSession().setAttribute("usuario",u);

            return null;
        } else {
            return "/login.jsp";
        }
    }
}
