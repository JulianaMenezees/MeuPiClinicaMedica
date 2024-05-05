package controller;

import javax.swing.JOptionPane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import model.Medico;

public class MedicoDao extends ConectarDao {

    private PreparedStatement ps;

    public MedicoDao() {
        super();
    }

    public void incluir(Medico obj) {

        sql = "INSERT INTO MEDICO VALUES (?, ?, ?, ?)";

        try {

            ps = con.prepareStatement(sql);

            ps.setString(1, obj.getNomeMedico());
            ps.setString(2, obj.getCpf());
            ps.setString(3, obj.getCrm());
            ps.setInt(4, obj.getEspecialidade());
            ps.execute();
            ps.close();

            JOptionPane.showMessageDialog(null, "Registro Incluído com Sucesso!");

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir usuário!" + err.getMessage());
        }
    }

    public ResultSet buscartodos() {

        sql = "SELECT * FROM MEDICO ORDER BY nome ";

        try {
            ps = con.prepareStatement(sql);

            return ps.executeQuery();

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar medico!" + err.getMessage());

            return null;
        }
    }

    public ResultSet buscar(Medico obj) {

        sql = "SELECT * FROM MEDICO WHERE CPF = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, obj.getCpf());
            return ps.executeQuery();
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar medico!" + err.getMessage());
            return null;
        }
    }

    public void excluir(String cpf) {

        sql = "DELETE FROM MEDICO WHERE CPF = '" + cpf + "'";

        try {
            ps = con.prepareStatement(sql);

            ps.execute();

            ps.close();

            JOptionPane.showMessageDialog(null, "Registro Excluido com Sucesso!");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir medico!" + err.getMessage());
        }
    }

    public void alterar(Medico obj) {
        sql = "UPDATE MEDICO SET nome = ?, cpf = ?, crm = ?, especialidade = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, obj.getNomeMedico());
            ps.setString(2, obj.getCpf());
            ps.setString(3, obj.getCrm());
            ps.setInt(4, obj.getEspecialidade());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Registro Alterado com Sucesso!");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao Alterar medico!" + err.getMessage());
        }
    }
}
