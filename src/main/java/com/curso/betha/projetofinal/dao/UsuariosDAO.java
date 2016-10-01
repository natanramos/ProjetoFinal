package com.curso.betha.projetofinal.dao;

import com.curso.betha.projetofinal.utils.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by NatanRamos on 01/10/2016.
 */
public class UsuariosDAO {

    public Integer RealizarLogin(String login, String senha) {
        Conexao conexao = new Conexao();
        Integer retorno = null;
        Connection conn = conexao.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
          String sql = "select i_usuarios as retorno from usuarios where login = trim(?) and senha = trim(?)";
          pstm = conn.prepareStatement(sql);

          pstm.setString(1,login);
          pstm.setString(2,senha);

          rs = pstm.executeQuery();
          if(rs.next()) {
              retorno = rs.getInt("retorno");
          }

          if(retorno == null){
              retorno = 0;
          }

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            if(pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return retorno;
    }
}
