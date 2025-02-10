package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoDao {

    public void conectar() {
        try (Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
                "leo150105")) {
            if (conexao != null) {
                System.out.println("Conectado ao banco de dados");
                Statement stm = conexao.createStatement();
                consultaDados(stm);
            } else {
                System.out.println("Falha ao conectar ao banco de dados");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados");
            e.printStackTrace();
        }
    }

    private void consultaDados(Statement stm) {
        // Implementação do método consultaDados
    }
}
