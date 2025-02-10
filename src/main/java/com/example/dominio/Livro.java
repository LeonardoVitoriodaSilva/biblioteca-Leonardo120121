package com.example.dominio;

// Classe que representa um livro na biblioteca
public class Livro {
    private String autor;
    private String titulo;
    private String editora;
    private int ano;
    private boolean emprestado = false;

    // Construtor da classe Livro
    public Livro(String autor, String titulo, String editora, int ano) {
        this.autor = autor;
        this.titulo = titulo;
        this.editora = editora;
        this.ano = ano;
    }

    // Método para obter o autor do livro
    public String getAutor() {
        return autor;
    }

    // Método para definir o autor do livro
    public void setAutor(String autor) {
        this.autor = autor;
    }

    // Método para obter o título do livro
    public String getTitulo() {
        return titulo;
    }

    // Método para definir o título do livro
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Método para obter a editora do livro
    public String getEditora() {
        return editora;
    }

    // Método para definir a editora do livro
    public void setEditora(String editora) {
        this.editora = editora;
    }

    // Método para obter o ano de publicação do livro
    public int getAno() {
        return ano;
    }

    // Método para definir o ano de publicação do livro
    public void setAno(int ano) {
        this.ano = ano;
    }

    // Método para verificar se o livro está emprestado
    public boolean isEmprestado() {
        return emprestado;
    }

    // Método para definir o status de empréstimo do livro
    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    // Método para representar o livro como uma string
    @Override
    public String toString() {
        return "Livro{" +
                "autor='" + autor + '\'' +
                ", titulo='" + titulo + '\'' +
                ", editora='" + editora + '\'' +
                ", ano=" + ano +
                ", emprestado=" + emprestado +
                '}';
    }
}