package src.main.java.com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.main.java.com.example.dominio.Livro;

@SuppressWarnings("unused")
public class App {
    public static void main(String[] args) {
        // Criação de uma lista de livros
        List<Livro> livros = new ArrayList<>();
        inicializarLivros(livros);

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                // Exibição do menu de opções
                System.out.println("\n1 - Cadastrar livro\n2 - Listar todos os livros\n3 - Pegar livro emprestado\n4 - Devolver livro\n5 - Listar livros emprestados e disponíveis\n6 - Listar histórico de empréstimos do usuário\n7 - Sair");
                System.out.print("\nDigite uma opção:");
                int escolha = scanner.nextInt();

                // Processamento da escolha do usuário
                switch (escolha) {
                    case 1:
                        cadastrarLivro(scanner, livros);
                        break;
                    case 2:
                        listarLivros(livros);
                        break;
                    case 3:
                        pegarLivroEmprestado(scanner, livros);
                        break;
                    case 4:
                        devolverLivro(scanner, livros);
                        break;
                    case 5:
                        listarLivrosEmprestadosDisponiveis(livros);
                        break;
                    case 6:
                        listarHistoricoEmprestimos();
                        break;
                    case 7:
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

        livros.add(novoLivro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    // Lista todos os livros cadastrados
    private static void listarLivros(List<Livro> livros) {
        System.out.println("\nLista de Livros:");
        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }

    // Marca um livro como emprestado
    private static void pegarLivroEmprestado(Scanner scanner, List<Livro> livros) {
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("Digite o título do livro a ser emprestado: ");
        String titulo = scanner.nextLine();

        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo) && !livro.isEmprestado()) {
                livro.setEmprestado(true);
                System.out.println("Livro emprestado com sucesso!");
                return;
            }
        }
        System.out.println("Livro não encontrado ou já emprestado.");
    }

    // Marca um livro como devolvido
    private static void devolverLivro(Scanner scanner, List<Livro> livros) {
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("Digite o título do livro a ser devolvido: ");
        String titulo = scanner.nextLine();

        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo) && livro.isEmprestado()) {
                livro.setEmprestado(false);
                System.out.println("Livro devolvido com sucesso!");
                return;
            }
        }
        System.out.println("Livro não encontrado ou não está emprestado.");
    }

    // Lista os livros emprestados e disponíveis
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

    // Lista o histórico de empréstimos (não implementado)
    private static void listarHistoricoEmprestimos() {
        System.out.println("Funcionalidade não implementada.");
    }
}