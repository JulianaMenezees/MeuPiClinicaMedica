package controller;

import model.funcionario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class FuncionarioDao extends ConectarDao {

    private PreparedStatement ps;

    public FuncionarioDao() {
        super();
    }

    public void incluir(funcionario obj) {

        sql = "INSERT INTO FUNCIONARIOS VALUES (?, ?, ?, ?, ?, ?)";

        try {

            ps = con.prepareStatement(sql);

            ps.setString(1, obj.getNomeFuncionario());
            ps.setString(2, obj.getCpfFuncionario());
            ps.setString(3, obj.getTelefone());
            ps.setString(4, obj.getEndereco());
            ps.setInt(5, obj.getCargo());
            ps.setString(6, obj.getSenha());
            ps.execute();
            ps.close();

            JOptionPane.showMessageDialog(null, "Registro Incluído com Sucesso!");

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir funcionario!" + err.getMessage());
        }
    }

    public ResultSet buscartodos() {

        sql = "SELECT * FROM FUNCIONARIOS ORDER BY cpf ";

        try {
            ps = con.prepareStatement(sql);

            return ps.executeQuery();

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar funcionarios!" + err.getMessage());

            return null;
        }
    }

    public ResultSet buscar(funcionario obj) {

        sql = "SELECT * FROM FUNCIONARIOS WHERE CPF = ?";

        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, obj.getCpfFuncionario());

            return ps.executeQuery();
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar funcionarios!" + err.getMessage());
            return null;
        }
    }

    public void excluir(String cpf) {

        sql = "DELETE FROM FUNCIONARIOS WHERE CPF = '" + cpf + "'";

        try {
            ps = con.prepareStatement(sql);

            ps.execute();

            ps.close();

            JOptionPane.showMessageDialog(null, "Registro Excluido com Sucesso!");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir funcionarios!" + err.getMessage());
        }
    }

    public void alterar(funcionario obj) {
        sql = "UPDATE FUNCIONARIOS SET cpf = ?, nome = ?, telefone = ?, endereco = ?, cargo = ?, senha = ?";
        try {
            ps.setString(1, obj.getNomeFuncionario());
            ps.setString(2, obj.getCpfFuncionario());
            ps.setString(3, obj.getTelefone());
            ps.setString(4, obj.getEndereco());
            ps.setInt(5, obj.getCargo());
            ps.setString(6, obj.getSenha());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Registro Alterado com Sucesso!");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao Alterar funcionario!" + err.getMessage());
        }
    }

    public ResultSet validarLogin(String login, String senha) {
        sql = "Select * from funcionarios where cpf='" + login + "'"
                + " and senha = '" + senha + "'";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ResultSet resul = ps.executeQuery();
            return resul;
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
            return null;
        }
    }

}
