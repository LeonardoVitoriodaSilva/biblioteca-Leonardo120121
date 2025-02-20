package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDao {

    public static Connection conectar() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "leo150105";

        try {
            Connection conexao = DriverManager.getConnection(url, user, password);
            if (conexao != null) {
                System.out.println("Conectado ao banco de dados");
                return conexao;
            } else {
                System.out.println("Falha ao conectar ao banco de dados");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return null;
    }
}


