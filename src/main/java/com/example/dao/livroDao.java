package com.example.dao;

import com.example.dominio.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDao {
    private Connection connection;

    public LivroDao(Connection connection) {
        this.connection = connection;
    }

    public void adicionarLivro(Livro livro) throws SQLException {
        String sql = "INSERT INTO livros (titulo, autor, editora, ano, emprestado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getEditora());
            stmt.setInt(4, livro.getAno());
            stmt.setBoolean(5, livro.isEmprestado());
            stmt.executeUpdate();
            System.out.println("Livro cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao salvar livro: " + e.getMessage());
        }
    }

    public List<Livro> listarLivros() throws SQLException {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Livro livro = new Livro(
                    rs.getString("autor"),
                    rs.getString("titulo"),
                    rs.getString("editora"),
                    rs.getInt("ano")
                );
                livro.setId(rs.getInt("id"));
                livro.setEmprestado(rs.getBoolean("emprestado"));
                livros.add(livro);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar livros: " + e.getMessage());
        }
        return livros;
    }

    public void atualizarLivro(Livro livro) throws SQLException {
        String sql = "UPDATE livros SET titulo = ?, autor = ?, editora = ?, ano = ?, emprestado = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getEditora());
            stmt.setInt(4, livro.getAno());
            stmt.setBoolean(5, livro.isEmprestado());
            stmt.setInt(6, livro.getId());
            stmt.executeUpdate();
            System.out.println("Livro atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar livro: " + e.getMessage());
        }
    }
}