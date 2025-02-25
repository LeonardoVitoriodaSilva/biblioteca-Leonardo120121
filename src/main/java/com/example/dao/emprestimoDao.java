package com.example.dao;

import com.example.dominio.Emprestimo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDao {
    private Connection connection;

    public EmprestimoDao(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Emprestimo emprestimo) throws SQLException {
        String sql = "INSERT INTO emprestimo (usuario_id, livro_id, data_emprestimo) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, emprestimo.getUsuarioId());
            stmt.setInt(2, emprestimo.getLivroId());
            stmt.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
            // stmt.setDate(4, Date.valueOf(emprestimo.getDataDevolucao()));
            stmt.executeUpdate();

            System.out.println("Empréstimo cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao salvar empréstimo: " + e.getMessage());
        }
    }

    public List<Emprestimo> buscarTodos() throws SQLException {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT usuario_id, livro_id, data_emprestimo, data_devolucao FROM emprestimo";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo(
                    rs.getInt("usuario_id"),
                    rs.getInt("livro_id"),
                    rs.getDate("data_emprestimo").toLocalDate(),
                    rs.getDate("data_devolucao").toLocalDate()
                );
                emprestimos.add(emprestimo);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar empréstimos: " + e.getMessage());
        }

        return emprestimos;
    }

    public Emprestimo buscarPorId(int id)  throws SQLException  {
        String sql = "SELECT * FROM emprestimo WHERE id = ?";
        Emprestimo emprestimo = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                emprestimo = new Emprestimo(
                    resultSet.getInt("usuario_id"),
                    resultSet.getInt("livro_id"),
                    resultSet.getDate("data_emprestimo").toLocalDate(),
                    resultSet.getDate("data_devolucao").toLocalDate()
                );
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar empréstimo: " + e.getMessage());
        }
        return emprestimo;
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM emprestimo WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Empréstimo deletado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao deletar empréstimo: " + e.getMessage());
        }
    }
}