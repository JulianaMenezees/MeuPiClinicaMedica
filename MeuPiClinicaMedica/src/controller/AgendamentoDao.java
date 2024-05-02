package controller;

import controller.ConectarDao;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import model.agendamento;

public class AgendamentoDao extends ConectarDao {

    private PreparedStatement ps;

    public AgendamentoDao() {
        super();
    }

    public void incluir(agendamento obj) {

        sql = "INSERT INTO AGENDAMENTO VALUES (?, ?, ?, ?, ?)";

        try {

            ps = con.prepareStatement(sql);

            ps.setString(1, obj.getCpfPac());
            ps.setString(2, obj.getNomePaciente());
            ps.setString(3, obj.getCRM());
            ps.setString(4, obj.getNomeMedico());
            ps.setInt(5, obj.getIdConsulta());
            ps.execute();
            ps.close();

            JOptionPane.showMessageDialog(null, "Registro Incluído com Sucesso!");

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir agendamento!" + err.getMessage());
        }
    }

    public ResultSet buscartodos() {

        sql = "SELECT * FROM AGENDAMENTO ORDER BY CpfPac ";

        try {
            ps = con.prepareStatement(sql);

            return ps.executeQuery();

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar agendamento!" + err.getMessage());

            return null;
        }
    }

    public ResultSet buscar(agendamento obj) {

        sql = "SELECT * FROM AGENDAMENTO WHERE CpfPac = ?";

        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, obj.getCpfPac());

            return ps.executeQuery();
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar agendamento!" + err.getMessage());
            return null;
        }
    }

    public void excluir(String cpf) {

        sql = "DELETE FROM AGENDAMENTO WHERE CPF = '" + cpf + "'";

        try {
            ps = con.prepareStatement(sql);

            ps.execute();

            ps.close();

            JOptionPane.showMessageDialog(null, "Registro Excluido com Sucesso!");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir agendamento!" + err.getMessage());
        }
    }

    public void alterar(agendamento obj) {
        sql = "UPDATE AGENDAMENTO SET CpfPac = ?, nomePaciente = ?, crm = ?, nomeMedico =  ?, idConsulta = ?";
        try {
            ps.setString(1, obj.getCpfPac());
            ps.setString(2, obj.getNomePaciente());
            ps.setString(3, obj.getCRM());
            ps.setString(4, obj.getNomeMedico());
            ps.setInt(5, obj.getIdConsulta());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Registro Alterado com Sucesso!");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao Alterar agendamento!" + err.getMessage());
        }
    }

}
