package com.curso.betha.projetofinal.dao;

import com.curso.betha.projetofinal.model.Usuarios;
import com.curso.betha.projetofinal.utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by NatanRamos on 01/10/2016.
 */
public class UsuariosDAO {

    public Usuarios inserir(Usuarios usuario) {
        Connection conn = Conexao.getConnection();
        PreparedStatement pstm = null;
        Long id = this.getNovoCodUsuario();
        try {
            String sql = "insert into public.usuarios values(?,?,?,?,?,?)";
            pstm = conn.prepareStatement(sql);

            pstm.setLong(1, id);
            pstm.setString(2, usuario.getNome());
            pstm.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
            if (usuario.getEmail() != null && !"".equals(usuario.getEmail())) {
                pstm.setString(4, usuario.getEmail());
            } else {
                pstm.setNull(4, Types.VARCHAR);
            }
            pstm.setString(5, usuario.getLogin());
            pstm.setString(6, usuario.getSenha());

            pstm.executeUpdate();

            return this.getUsuario(id);

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
        return null;
    }

    public Usuarios atualizar(Usuarios usuario) {
        Connection conn = Conexao.getConnection();
        PreparedStatement pstm = null;
        try {
            String sql = "update public.usuarios set nome=?, email=?, login=?, senha=? where id = ?";
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, usuario.getNome());
            if (usuario.getEmail() != null && !"".equals(usuario.getEmail())) {
                pstm.setString(2, usuario.getEmail());
            } else {
                pstm.setNull(2, Types.VARCHAR);
            }
            pstm.setString(3, usuario.getLogin());
            pstm.setString(4, usuario.getSenha());
            pstm.setLong(5, usuario.getId());

            pstm.executeUpdate();

            return this.getUsuario(usuario.getId());

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
        return null;
    }

    public static void excluir(Long codigo) {
        Connection conn = Conexao.getConnection();
        PreparedStatement pstm = null;
        try {
            String sql = "delete from public.usuarios where id = ?";
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

    public Usuarios getUsuario(Long codigo) {
        Usuarios usuario = null;
        Connection conn = Conexao.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            String sql = "select id, nome, data_cadastro, email, login, senha from public.usuarios where id = ?";
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
            return usuario;
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
        return null;
    }

    public static List<Usuarios> getUsuarios() {
        List<Usuarios> lista = new ArrayList<Usuarios>();
        Connection conn = Conexao.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            String sql = "select id, nome, data_cadastro, email, login, senha from public.usuarios order by usuarios.id";
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

    public static Long getNovoCodUsuario() {
        Connection conn = Conexao.getConnection();
        Long retorno = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            String sql = "select nextval('seq_usuarios') as retorno";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if(rs.next()) {
                retorno = rs.getLong("retorno");
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

    public static Long realizarLogin(String login, String senha) {
        Long retorno = null;
        Connection conn = Conexao.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
          String sql = "select min(id) as retorno from public.usuarios where login = trim(?) and senha = trim(?)";
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
