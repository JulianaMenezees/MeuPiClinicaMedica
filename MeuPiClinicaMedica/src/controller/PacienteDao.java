package controller;

import controller.ConectarDao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import model.Paciente;

public class PacienteDao extends ConectarDao {

    private PreparedStatement ps;

    public PacienteDao() {
        super(); 
    }

    public void incluir(Paciente obj) {

        sql = "INSERT INTO PACIENTE VALUES (?, ?, ?, ?, ?, ?)";

        try {

            ps = con.prepareStatement(sql);

            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getCPF());
            ps.setString(3, obj.getRG());
            ps.setString(4, obj.getTelefone());
            ps.setString(5, obj.getnCarteirinha());
            ps.setString(6, obj.getEmail());
            ps.execute();
            ps.close();

            JOptionPane.showMessageDialog(null, "Registro Incluído com Sucesso!");

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir paciente! " + err.getMessage());
        }
    }

    public ResultSet buscartodos() {

        sql = "SELECT * FROM PACIENTE ORDER BY cpf ";

        try {
            ps = con.prepareStatement(sql);

            return ps.executeQuery();

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar paciente!" + err.getMessage());

            return null;
        }
    }

    public ResultSet buscar(Paciente obj) {

        sql = "SELECT * FROM PACIENTE WHERE CPF = ?";

        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, obj.getCPF());

            return ps.executeQuery();
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar paciente!" + err.getMessage());
            return null;
        }
    }

    public void excluir(String cpf) {

        sql = "DELETE FROM PACIENTE WHERE CPF = '" + cpf + "'";

        try {
            ps = con.prepareStatement(sql);

            ps.execute();

            ps.close();

            JOptionPane.showMessageDialog(null, "Registro Excluido com Sucesso!");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir paciente!" + err.getMessage());
        }
    }

    public void alterar(Paciente obj) {
        sql = "UPDATE PACIENTE SET nome = ?, cpf = ?, rg = ?, celular =  ?, nCarteirinha = ?, email = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getCPF());
            ps.setString(3, obj.getRG());
            ps.setString(4, obj.getTelefone());
            ps.setString(5, obj.getnCarteirinha());
            ps.setString(6, obj.getEmail());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Registro Alterado com Sucesso!");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao Alterar paciente!" + err.getMessage());
        }
    }

}
