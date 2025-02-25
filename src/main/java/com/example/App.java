package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.SQLException;
import java.time.LocalDate;

import com.example.dao.ConexaoDao;
import com.example.dao.LivroDao;
import com.example.dao.UsuarioDao;
import com.example.dominio.Emprestimo;
import com.example.dominio.Livro;
import com.example.dominio.Usuario;

public class App {
    public static void main(String[] args) {
        // Criação de uma lista de livros
        List<Livro> livros = new ArrayList<>();
        inicializarLivros(livros);

        // Criação de uma lista de usuários
        List<Usuario> usuarios = new ArrayList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                // Exibição do menu de opções
                System.out.println("\n1 - Cadastrar usuário\n2 - Listar todos os livros\n3 - Cadastrar livro\n4 - Pegar livro emprestado\n5 - Devolver livro\n6 - Listar livros emprestados e disponíveis\n7 - Listar histórico de empréstimos do usuário\n8 - Listar usuários\n9 - Sair");
                System.out.print("\nDigite uma opção:");

                // Verificação para garantir que o valor inserido seja um número inteiro
                if (!scanner.hasNextInt()) {
                    System.out.println("Opção inválida! Por favor, digite um número inteiro.");
                    scanner.next(); // Consumir a entrada inválida
                    continue;
                }

                int escolha = scanner.nextInt();

                // Processamento da escolha do usuário
                switch (escolha) {
                    case 1:
                        cadastrarUsuario(scanner, usuarios);
                        break;
                    case 2:
                        listarLivros();
                        break;
                    case 3:
                        cadastrarLivro(scanner, livros);
                        break;
                    case 4:
                        pegarLivroEmprestado(scanner, livros, usuarios);
                        break;
                    case 5:
                        devolverLivro(scanner, livros);
                        break;
                    case 6:
                        listarLivrosEmprestadosDisponiveis(livros);
                        break;
                    case 7:
                        listarHistoricoEmprestimos(scanner, usuarios);
                        break;
                    case 8:
                        listarUsuarios(usuarios);
                        break;
                    case 9:
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        }
    }

    // Inicializa a lista de livros com alguns exemplos
    private static void inicializarLivros(List<Livro> livros) {
        Livro livro = new Livro("J.K Rowling", "Harry Potter e a Pedra Filosofal", "Bloomsbury", 1997);
        Livro livro1 = new Livro("J.K Rowling", "Harry Potter e a Câmara Secreta", "Bloomsbury", 1998);
        Livro livro2 = new Livro("J.K Rowling", "Harry Potter e o Prisioneiro de Azkaban", "Bloomsbury", 1999);

        livros.add(livro);
        livros.add(livro1);
        livros.add(livro2);
    }

    // Cadastra um novo livro na lista
    private static void cadastrarLivro(Scanner scanner, List<Livro> livros) {
        scanner.nextLine(); // Consumir a nova linha
        Livro novoLivro = new Livro(null, null, null, 0);

        System.out.print("Digite o título do livro: ");
        novoLivro.setTitulo(scanner.nextLine());

        System.out.print("Digite o autor do livro: ");
        novoLivro.setAutor(scanner.nextLine());

        System.out.print("Digite a editora do livro: ");
        novoLivro.setEditora(scanner.nextLine());

        System.out.print("Digite o ano do livro: ");
        novoLivro.setAno(scanner.nextInt());

        try (var conexao = ConexaoDao.conectar()) {
            new LivroDao(conexao).adicionarLivro(novoLivro);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        livros.add(novoLivro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    // Lista todos os livros cadastrados
    private static void listarLivros() {
        System.out.println("\nLista de Livros:");
        try (var conexao = ConexaoDao.conectar()) {
            List<Livro> listaDeLivros = new LivroDao(conexao).listarLivros();
            for (Livro livro : listaDeLivros) {
                System.out.println(livro.getTitulo() + " - " + livro.getAutor());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Marca um livro como emprestado
    private static void pegarLivroEmprestado(Scanner scanner, List<Livro> livros, List<Usuario> usuarios) {
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("Digite o título do livro a ser emprestado: ");
        String titulo = scanner.nextLine();

        System.out.print("Digite o CPF do usuário: ");
        String cpf = scanner.nextLine();

        try {
            Usuario usuario = new UsuarioDao().buscarUsuarioPorCpf(cpf);
            if (usuario == null) {
                System.out.println("Usuário não encontrado.");
                return;
            }

            try (var conexao = ConexaoDao.conectar()) {
                LivroDao livroDao = new LivroDao(conexao);
                for (Livro livro : livros) {
                    if (livro.getTitulo().equalsIgnoreCase(titulo) && !livro.isEmprestado()) {
                        livro.setEmprestado(true);
                        livroDao.atualizarLivro(livro);
                        Emprestimo emprestimo = new Emprestimo(usuario.getId(), livro.getId(), LocalDate.now(), null);
                        usuario.adicionarEmprestimo(emprestimo);
                        System.out.println("Livro emprestado com sucesso!");
                        return;
                    }
                }
                System.out.println("Livro não encontrado ou já emprestado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Marca um livro como devolvido
    
    private static void devolverLivro(Scanner scanner, List<Livro> livros) {
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("Digite o título do livro a ser devolvido: ");
        String titulo = scanner.nextLine();

        try (var conexao = ConexaoDao.conectar()) {
            LivroDao livroDao = new LivroDao(conexao);
            for (Livro livro : livros) {
                if (livro.getTitulo().equalsIgnoreCase(titulo) && livro.isEmprestado()) {
                    livro.setEmprestado(false);
                    livroDao.atualizarLivro(livro);
                    System.out.println("Livro devolvido com sucesso!");
                    return;
                }
            }
            System.out.println("Livro não encontrado ou não está emprestado.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lista os livros emprestados e disponíveis
    // Não conectado
    private static void listarLivrosEmprestadosDisponiveis(List<Livro> livros) {
        System.out.println("\nLivros Emprestados:");
        for (Livro livro : livros) {
            if (livro.isEmprestado()) {
                System.out.println(livro);
            }
        }

        System.out.println("\nLivros Disponíveis:");
        for (Livro livro : livros) {
            if (!livro.isEmprestado()) {
                System.out.println(livro);
            }
        }
    }

    // Lista o histórico de empréstimos do usuário
    private static void listarHistoricoEmprestimos(Scanner scanner, List<Usuario> usuarios) {
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("Digite o CPF do usuário: ");
        String cpf = scanner.nextLine();

        Usuario usuario = buscarUsuarioPorCpf(usuarios, cpf);
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        List<Emprestimo> historico = usuario.getHistoricoEmprestimos();
        if (historico.isEmpty()) {
            System.out.println("Nenhum empréstimo encontrado para este usuário.");
        } else {
            System.out.println("\nHistórico de Empréstimos:");
            for (Emprestimo emprestimo : historico) {
                System.out.println(emprestimo);
            }
        }
    }

    // Busca um usuário pelo CPF
    private static Usuario buscarUsuarioPorCpf(List<Usuario> usuarios, String cpf) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                return usuario;
            }
        }
        return null;
    }

    // Cadastra um novo usuário na lista
    private static void cadastrarUsuario(Scanner scanner, List<Usuario> usuarios) {
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o email do usuário: ");
        String email = scanner.nextLine();

        System.out.print("Digite o CPF do usuário: ");
        String cpf = scanner.nextLine();

        Usuario novoUsuario = new Usuario(nome, email, cpf);
        try {
            new UsuarioDao().adicionarUsuario(novoUsuario);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        usuarios.add(novoUsuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    // Lista todos os usuários cadastrados
    //Conectado
    private static void listarUsuarios(List<Usuario> usuarios) {
        System.out.println("\nLista de Usuários:");
        try {
            List<Usuario> listaDeUsuarios = new UsuarioDao().listarUsuarios();
            for (Usuario usuario : listaDeUsuarios) {
                System.out.println(usuario.getNome() + " - " + usuario.getEmail());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

