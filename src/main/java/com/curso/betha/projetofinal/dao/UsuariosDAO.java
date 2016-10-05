package com.curso.betha.projetofinal.dao;

import com.curso.betha.projetofinal.model.Usuarios;
import com.curso.betha.projetofinal.utils.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by NatanRamos on 01/10/2016.
 */
public class UsuariosDAO {

    public static void salvar(Usuarios usuario) {
        Connection conn = Conexao.getConnection();
        PreparedStatement pstm = null;
        try {
            String sql = "insert into usuarios values(?,?,?,?,?)";
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, usuario.getNome());
            pstm.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
            pstm.setString(3, usuario.getEmail());
            pstm.setString(4, usuario.getLogin());
            pstm.setString(5, usuario.getSenha());

            pstm.executeUpdate();
        } catch(SQLException e) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if(pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void atualizar(Usuarios usuario) {
        Connection conn = Conexao.getConnection();
        PreparedStatement pstm = null;
        try {
            String sql = "update usuarios set nome=?, email=?, login=?, senha=? where id = ?";
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getEmail());
            pstm.setString(3, usuario.getLogin());
            pstm.setString(4, usuario.getSenha());
            pstm.setLong(5, usuario.getId());

            pstm.executeUpdate();
        } catch(SQLException e) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if(pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void excluir(Long codigo) {
        Connection conn = Conexao.getConnection();
        PreparedStatement pstm = null;
        try {
            String sql = "delete from usuarios where id = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setLong(1, codigo);
            pstm.executeUpdate();
        } catch(SQLException e) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if(pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static Usuarios getUsuario(Long codigo) {
        Usuarios usuario = null;
        Connection conn = Conexao.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            String sql = "select id, nome, data_cadastro, email, login, senha from usuarios where id = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setLong(1, codigo);
            rs = pstm.executeQuery();
            if(rs.next()) {
                usuario = new Usuarios();
                usuario.setId(rs.getLong("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setDataCadastro(rs.getDate("data_cadastro"));
                usuario.setEmail(rs.getString("email"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
            }
        } catch(SQLException e) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if(pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return usuario;
    }

    public static List<Usuarios> getUsuarios() {
        List<Usuarios> lista = new ArrayList<Usuarios>();
        Connection conn = Conexao.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            String sql = "select id, nome, data_cadastro, email, login, senha from usuarios";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while(rs.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setId(rs.getLong("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setDataCadastro(rs.getDate("data_cadastro"));
                usuario.setEmail(rs.getString("email"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                lista.add(usuario);
            }
        } catch(SQLException e) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if(pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return lista;
    }

    public static Long realizarLogin(String login, String senha) {
        Long retorno = null;
        Connection conn = Conexao.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
          String sql = "select id as retorno from usuarios where login = trim(?) and senha = trim(?)";
          pstm = conn.prepareStatement(sql);

          pstm.setString(1,login);
          pstm.setString(2,senha);

          rs = pstm.executeQuery();
          if(rs.next()) {
              retorno = rs.getLong("retorno");
          }

          if(retorno == null){
              retorno = 0L;
          }

        } catch(SQLException e) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if(pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return retorno;
    }
}
