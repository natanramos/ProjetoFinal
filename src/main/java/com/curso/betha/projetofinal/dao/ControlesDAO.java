package com.curso.betha.projetofinal.dao;

import com.curso.betha.projetofinal.model.Controles;
import com.curso.betha.projetofinal.model.Pessoas;
import com.curso.betha.projetofinal.utils.Conexao;
import com.curso.betha.projetofinal.utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by NatanRamos on 24/10/2016.
 */
public class ControlesDAO {

    public Controles inserir(Controles controle) {
        Connection conn = Conexao.getConnection();
        PreparedStatement pstm = null;
        Long id = this.getNovoCodControle();
        try {
            String sql = "insert into public.controles values(?,?,?,?,?,?,?,?,?,?,?,?)";
            pstm = conn.prepareStatement(sql);

            pstm.setLong(1, id);
            pstm.setString(2, controle.getMensalista());
            if (controle.getIdPessoas() != null) {
                pstm.setLong(3, controle.getIdPessoas());
            } else {
                pstm.setNull(3, Types.INTEGER);
            }
            pstm.setString(4, controle.getPlaca());
            if (controle.getMarca() != null && !"".equals(controle.getMarca())) {
                pstm.setString(5, controle.getMarca());
            } else {
                pstm.setNull(5, Types.VARCHAR);
            }
            if (controle.getModelo() != null && !"".equals(controle.getModelo())) {
                pstm.setString(6, controle.getModelo());
            } else {
                pstm.setNull(6, Types.VARCHAR);
            }
            if (controle.getCor() != null && !"".equals(controle.getCor())) {
                pstm.setString(7, controle.getCor());
            } else {
                pstm.setNull(7, Types.VARCHAR);
            }
            if (controle.getResponsavel() != null && !"".equals(controle.getResponsavel())) {
                pstm.setString(8, controle.getResponsavel());
            } else {
                pstm.setNull(8, Types.VARCHAR);
            }
            if (controle.getDataHoraEntrada() != null) {
                pstm.setDate(9, new Date(controle.getDataHoraEntrada().getTime()));
            } else {
                pstm.setNull(9, Types.DATE);
            }
            if (controle.getDataHoraSaida() != null) {
                pstm.setDate(10, new Date(controle.getDataHoraSaida().getTime()));
            } else {
                pstm.setNull(10, Types.DATE);
            }
            pstm.setString(11, controle.getSituacao());
            pstm.setDouble(12, controle.getValor());

            pstm.executeUpdate();

            return this.getControle(id);

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

    public Controles atualizar(Controles controle) {
        Connection conn = Conexao.getConnection();
        PreparedStatement pstm = null;
        try {
            String sql = "update public.controles set mensalista=?, id_pessoas=?, placa=?, marca=?, modelo=?, cor=?, responsavel=?, data_hora_entrada=?, data_hora_saida=?, situacao=?, valor=? where id = ?";
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, pessoa.getNome());
            pstm.setString(2, pessoa.getTipoPessoa());
            pstm.setString(3, Utils.limparString(pessoa.getDocumento()));
            if (pessoa.getDataNascimento() != null) {
                pstm.setDate(4, new Date(pessoa.getDataNascimento().getTime()));
            } else {
                pstm.setNull(4, Types.DATE);
            }
            pstm.setString(5, Utils.limparString(pessoa.getTelefone()));
            if (pessoa.getEmail() != null && !"".equals(pessoa.getEmail())) {
                pstm.setString(6, pessoa.getEmail());
            } else {
                pstm.setNull(6, Types.VARCHAR);
            }
            if (pessoa.getRua() != null && !"".equals(pessoa.getRua())) {
                pstm.setString(7, pessoa.getRua());
            } else {
                pstm.setNull(7, Types.VARCHAR);
            }
            if (pessoa.getNumero() != null && !"".equals(pessoa.getNumero())) {
                pstm.setString(8, pessoa.getNumero());
            } else {
                pstm.setNull(8, Types.VARCHAR);
            }
            if (pessoa.getIdMunicipios() != null) {
                pstm.setLong(9, pessoa.getIdMunicipios());
            } else {
                pstm.setNull(9, Types.INTEGER);
            }
            if (pessoa.getIdEstados() != null) {
                pstm.setLong(10, pessoa.getIdEstados());
            } else {
                pstm.setNull(10, Types.INTEGER);
            }
            pstm.setLong(11, pessoa.getId());

            pstm.executeUpdate();

            return this.getControle(controle.getId());

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
            String sql = "delete from public.controles where id = ?";
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

    public Controles getControle(Long codigo) {
        Controles controle = null;
        Connection conn = Conexao.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            String sql = "select id, mensalista, id_pessoas, placa, marca, modelo, cor, responsavel, data_hora_entrada, data_hora_saida, situacao, valor from public.controles where id = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setLong(1, codigo);
            rs = pstm.executeQuery();
            if(rs.next()) {
                controle = new Controles();
                controle.setId(rs.getLong("id"));
                controle.setMensalista(rs.getString("mensalista"));
                controle.setIdPessoas(rs.getLong("id_pessoas"));
                controle.setPlaca(rs.getString("placa"));
                controle.setMarca(rs.getString("marca"));
                controle.setModelo(rs.getString("modelo"));
                controle.setCor(rs.getString("cor"));
                controle.setResponsavel(rs.getString("responsavel"));
                controle.setDataHoraEntrada(rs.getDate("data_hora_entrada"));
                controle.setDataHoraSaida(rs.getDate("data_hora_saida"));
                controle.setSituacao(rs.getString("situacao"));
                controle.setValor(rs.getDouble("valor"));
            }
            return controle;
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

    public static List<Pessoas> getPessoas() {
        List<Pessoas> lista = new ArrayList<Pessoas>();
        Connection conn = Conexao.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            String sql = "select id, nome, tipo_pessoa, documento, data_nascimento, telefone, email, rua, numero, id_municipios, id_estados from public.pessoas order by pessoas.id";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while(rs.next()) {
                Pessoas pessoa = new Pessoas();
                pessoa.setId(rs.getLong("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setTipoPessoa(rs.getString("tipo_pessoa"));
                pessoa.setDocumento(rs.getString("documento"));
                pessoa.setDataNascimento(rs.getDate("data_nascimento"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setRua(rs.getString("rua"));
                pessoa.setNumero(rs.getString("numero"));
                pessoa.setIdMunicipios(rs.getLong("id_municipios"));
                pessoa.setIdEstados(rs.getLong("id_estados"));
                lista.add(pessoa);
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

    public static Long getNovoCodControle() {
        Connection conn = Conexao.getConnection();
        Long retorno = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            String sql = "select nextval('seq_controles') as retorno";
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
