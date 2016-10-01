package com.curso.betha.projetofinal.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by NatanRamos on 30/09/2016.
 */
public class Conexao {

    public Connection getConnection() {

        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "postgres");
        } catch(ClassNotFoundException e) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        } catch(SQLException e) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        }

        return conn;
    }
}
