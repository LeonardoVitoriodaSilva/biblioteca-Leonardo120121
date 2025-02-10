package com.example.dominio;

import java.time.LocalDate;

// Classe que representa um empréstimo de livro na biblioteca
public class Emprestimo {
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private Usuario usuario;

    // Construtor da classe Emprestimo
    public Emprestimo(LocalDate dataEmprestimo, Usuario usuario) {
        this.dataEmprestimo = dataEmprestimo;
        this.usuario = usuario;
    }

    // Método para obter a data de empréstimo
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    // Método para obter a data de devolução
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    // Método para definir a data de devolução
    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    // Método para obter o usuário
    public Usuario getUsuario() {
        return usuario;
    }

    // Método para definir o usuário
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // Método para representar o empréstimo como uma string
    @Override
    public String toString() {
        return "Emprestimo{" +
                "dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucao=" + dataDevolucao +
                ", usuario=" + usuario +
                '}';
    }
}
