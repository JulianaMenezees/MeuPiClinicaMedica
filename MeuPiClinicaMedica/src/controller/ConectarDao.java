package controller;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;

public class ConectarDao {

    public Connection con = null;
    public String sql = null;

    public ConectarDao() {
        String strcon = "jdbc:mysql://localhost:3306/consultoriomedico";//cria a string de conexão ao servidor xaamp 
        try {

            con = DriverManager.getConnection(strcon, "root", "");
            this.criarBanco();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Conexão com Mysql não realizada!\n" + ex);
        }
    }

    public void criarBanco() {
        /* Criando a tabela pacientes */
        sql = "CREATE TABLE IF NOT EXISTS PACIENTE ( "
                + "nome varchar(50) not null,"
                + "cpf varchar(50) not null,"
                + "rg  varchar(50) not null,"
                + "celular varchar (50) not null,"
                + "nCarteirinha varchar (50) not null,"
                + "email varchar (50) not null,"
                + "primary key (cpf) )";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            ps.close();
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao criar banco de dados" + err.getMessage());
        }

        /* Criando a tabela funcionarios */
        sql = "CREATE TABLE IF NOT EXISTS FUNCIONARIOS("
                + "nome varchar(50) not null,"
                + "cpf varchar(50) not null,"
                + "cargo varchar(50) not null,"
                + "senha int not null,"
                + "primary key (cpf) )";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            ps.close();
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao criar banco de dados" + err.getMessage());
        }


        /* Criando a tabela agendamento */
        sql = "CREATE TABLE IF NOT EXISTS AGENDAMENTO ("
                + "cpfPac varchar(50) not null,"
                + "nomePaciente varchar(50) not null,"
                + "crm varchar(50) not null,"
                + "nomeMedico varchar(50) not null,"
                + "idConsulta int not null AUTO_INCREMENT,"
                + "primary key (idConsulta))";
                 
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            ps.close();
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao criar banco de dados" + err.getMessage());
        }

        /* Criando a tabela medico*/
        sql = "CREATE TABLE IF NOT EXISTS MEDICO("
                + "nome varchar(50) not null,"
                + "cpf varchar (50) not null,"
                + "crm varchar(50) not null,"
                + "especialidade varchar(50) not null,"
                + "primary key ( crm ) )";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            ps.close();
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao criar banco de dados" + err.getMessage());
        }

    }

}
