package com.curso.betha.projetofinal.dao;

import com.curso.betha.projetofinal.model.Competencias;
import com.curso.betha.projetofinal.utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by NatanRamos on 13/10/2016.
 */
public class CompetenciasDAO {

    public Competencias inserir(Competencias competencia) {
        Connection conn = Conexao.getConnection();
        PreparedStatement pstm = null;
        Long id = this.getNovoCodCompetencia();
        try {
            String sql = "insert into public.competencias values(?,?,?,?,?)";
            pstm = conn.prepareStatement(sql);

            pstm.setLong(1, id);
            pstm.setString(2, competencia.getDescricao());
            pstm.setDate(3, new Date(competencia.getDataInicial().getTime()));
            pstm.setDate(4, new Date(competencia.getDataFinal().getTime()));
            pstm.setDate(5, new Date(competencia.getDataVencimento().getTime()));

            pstm.executeUpdate();

            return this.getCompetencia(id);

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

    public Competencias atualizar(Competencias competencia) {
        Connection conn = Conexao.getConnection();
        PreparedStatement pstm = null;
        try {
            String sql = "update public.competencias set descricao=?, data_inicial=?, data_final=?, data_vencimento=? where id = ?";
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, competencia.getDescricao());
            pstm.setDate(2, new Date(competencia.getDataInicial().getTime()));
            pstm.setDate(3, new Date(competencia.getDataFinal().getTime()));
            pstm.setDate(4, new Date(competencia.getDataVencimento().getTime()));
            pstm.setLong(5, competencia.getId());

            pstm.executeUpdate();

            return this.getCompetencia(competencia.getId());

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
            String sql = "delete from public.competencias where id = ?";
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

    public Competencias getCompetencia(Long codigo) {
        Competencias comptecia = null;
        Connection conn = Conexao.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            String sql = "select id, descricao, data_inicial, data_final, data_vencimento from public.competencias where id = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setLong(1, codigo);
            rs = pstm.executeQuery();
            if(rs.next()) {
                comptecia = new Competencias();
                comptecia.setId(rs.getLong("id"));
                comptecia.setDescricao(rs.getString("descricao"));
                comptecia.setDataInicial(rs.getDate("data_inicial"));
                comptecia.setDataFinal(rs.getDate("data_final"));
                comptecia.setDataVencimento(rs.getDate("data_vencimento"));
            }
            return comptecia;
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

    public static List<Competencias> getCompetencias() {
        List<Competencias> lista = new ArrayList<Competencias>();
        Connection conn = Conexao.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            String sql = "select id, descricao, data_inicial, data_final, data_vencimento from public.competencias order by usuarios.id";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while(rs.next()) {
                Competencias competencia = new Competencias();
                competencia.setId(rs.getLong("id"));
                competencia.setDescricao(rs.getString("descricao"));
                competencia.setDataInicial(rs.getDate("data_inicial"));
                competencia.setDataFinal(rs.getDate("data_final"));
                competencia.setDataVencimento(rs.getDate("data_vencimento"));
                lista.add(competencia);
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

    public static Long getNovoCodCompetencia() {
        Connection conn = Conexao.getConnection();
        Long retorno = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            String sql = "select nextval('seq_competencias') as retorno";
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
}
