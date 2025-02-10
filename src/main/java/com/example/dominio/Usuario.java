package src.main.java.com.example.dominio;

// Classe que representa um usuário da biblioteca
public class Usuario {
    private String nome;
    private String cpf;
    private String email;

    // Construtor da classe Usuario
    public Usuario(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    // Método para obter o nome do usuário
    public String getNome() {
        return nome;
    }

    // Método para obter o CPF do usuário
    public String getCpf() {
        return cpf;
    }

    // Método para obter o email do usuário
    public String getEmail() {
        return email;
    }

    // Método para representar o usuário como uma string
    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
