package com.curso.betha.projetofinal.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by NatanRamos on 30/09/2016.
 */
public class conexao {

    public Connection getConnection() {

        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "th123!@#");
        } catch(ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
