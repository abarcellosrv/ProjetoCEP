package br.com.cep.dao;

import br.com.cep.factory.ConnectionFactory;
import br.com.cep.model.Cadastro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CadastroDAO {

    public void save(Cadastro cadastro) {
        String sql = "INSERT INTO cadastro(nome, cep, numero, complemento) VALUES (?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, cadastro.getNome());
            pstm.setInt(2, Integer.parseInt(cadastro.getCep()));
            pstm.setInt(3, cadastro.getNumero());
            pstm.setString(4, cadastro.getComplemento());

            pstm.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public List<Cadastro> getContatos() throws SQLException {
        String sql = "SELECT * from cadastro";
        List<Cadastro> cadastros = new ArrayList<Cadastro>();
        Connection con = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            con = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement) con.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Cadastro cadastro = new Cadastro(rset.getString("nome"),
                        rset.getInt("cep"),
                        rset.getInt("numero"),
                        rset.getString("complemento"));

                cadastros.add(cadastro);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (rset != null)
                rset.close();
            if (con != null)
                con.close();
            if (pstm != null)
                pstm.close();
        }
        return cadastros;
    }

    public Cadastro getUltimoCadastro() throws SQLException {
        String sql = "SELECT * FROM cadastro WHERE id = (SELECT MAX(id) FROM cadastro)";

        Connection con = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        Cadastro ultimoCadastro = null;
        try {
            con = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement) con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rset = pstm.executeQuery();
            rset.first();
            ultimoCadastro = new Cadastro(
                    rset.getString("nome"),
                    rset.getInt("cep"),
                    rset.getInt("numero"),
                    rset.getString("complemento")
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (rset != null)
                rset.close();
            if (con != null)
                con.close();
            if (pstm != null)
                pstm.close();
        }
        return ultimoCadastro;
    }

    public Cadastro getCadastroPorID(Integer id) throws SQLException {
        String sql = "SELECT * FROM cadastro WHERE id = ?";

        Connection con = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        Cadastro cadastroID = null;
        try {
            con = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement) con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pstm.setInt(1,id);
            rset = pstm.executeQuery();
            rset.first();
            cadastroID = new Cadastro(
                    rset.getString("nome"),
                    rset.getInt("cep"),
                    rset.getInt("numero"),
                    rset.getString("complemento")
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (rset != null)
                rset.close();
            if (con != null)
                con.close();
            if (pstm != null)
                pstm.close();
        }
        return cadastroID;
    }

    public void deleteUser(Integer id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM cadastro WHERE id=?";

        Connection con = null;
        PreparedStatement pstm = null;

        Cadastro ultimoCadastro = null;

        try {
            con = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement) con.prepareStatement(sql);
            pstm.setInt(1,id);
            pstm.execute();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void updateUser(Integer id, String column, String value) throws SQLException, ClassNotFoundException{
        String sql = "UPDATE cadastro SET " +column + "= ? WHERE id = ?;";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            if(column == "cep" || column == "numero"){
                Integer valueInt = Integer.parseInt(value);
                pstm.setInt(1, valueInt);
            } else {
                pstm.setString(1, value);
            }
            pstm.setInt(2,id);
            pstm.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
