package com.example.dominio;

import java.util.ArrayList;
import java.util.List;

// Classe que representa um usuário da biblioteca
public class Usuario {
    private String nome;
    private String cpf;
    private String email;
    private List<Emprestimo> historicoEmprestimos;

    // Construtor da classe Usuario
    public Usuario(String nome, String email, String cpf) {
        this.nome = nome;
        this.email = email;
        this.cpf = formatarCpf(cpf);
        this.historicoEmprestimos = new ArrayList<>();
    }

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = formatarCpf(cpf);
    }

    // Método para formatar o CPF
    private String formatarCpf(String cpf) {
        // Remove todos os caracteres não numéricos
        cpf = cpf.replaceAll("\\D", "");
        // Aplica a formatação
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    // Método para adicionar um empréstimo ao histórico
    public void adicionarEmprestimo(Emprestimo emprestimo) {
        historicoEmprestimos.add(emprestimo);
    }

    // Método para listar o histórico de empréstimos
    public List<Emprestimo> getHistoricoEmprestimos() {
        return historicoEmprestimos;
    }

    // Método para representar o usuário como uma string
    @Override
    public String toString() {
        return "Usuario [nome=" + nome + ", email=" + email + ", cpf=" + cpf + "]";
    }
}
