
package com.example;

import com.example.dao.ConexaoDao;
import com.example.dao.UsuarioDao;
import com.example.dominio.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection conexao = ConexaoDao.conectar();
        if (conexao != null) {
            UsuarioDao usuarioDao = new UsuarioDao();

            try {
                // Adicionar um usuário
                Usuario novoUsuario = new Usuario("Leonardo Vitorio", "leonardo@gmail.com", "097.696.830-20");
                usuarioDao.adicionarUsuario(novoUsuario);

                // Listar todos os usuários
                List<Usuario> usuarios = usuarioDao.listarUsuarios();
                for (Usuario usuario : usuarios) {
                    System.out.println(usuario.getNome() + " - " + usuario.getEmail());
                }

                // Atualizar um usuário
                Usuario usuarioAtualizado = usuarios.get(0);
                usuarioAtualizado.setNome("Leonardo Vitorio Atualizado");
                usuarioDao.atualizarUsuario(usuarioAtualizado);

                // Remover um usuário
                usuarioDao.removerUsuario(usuarioAtualizado.getId());

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
