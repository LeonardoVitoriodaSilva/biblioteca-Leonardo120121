import dominio.Emprestimo;
import dominio.Livro;
import dominio.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@SuppressWarnings("unused")
public class App {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
            System.out.println("\n1 - Cadastrar livro\n2 - Listar todos os livros\n3 - Pegar livro emprestado\n4 - Devolver livro\n5 - Listar livros emprestados e disponíveis\n6 - Listar histórico de empréstimos do usuário\n7 - Sair");
            System.out.print("\nDigite uma opção:");
            int escolha = scanner.nextInt();    
            
            Livro livro = new Livro();
            livro.setAutor("J.K Rowling.");
            livro.setTitulo("Harry Potter e a Pedra Filosofal.");
            livro.setEditora("Bloomsbury.");
            livro.setAno(1997);

            Livro livro1 = new Livro();
            livro1.setAutor("J.K Rowling");
            livro1.setTitulo("Harry Potter e a Câmara secreta.");
            livro1.setEditora("Bloomsbury.");
            livro1.setAno(1998);

            Livro livro2 = new Livro();
            livro2.setAutor("J.K Rowling.");
            livro2.setTitulo("Harry Potter e o Prisioneiro de Azkaban");
            livro2.setEditora("Bloomsbury.");
            livro2.setAno(1999);
            
            ArrayList<Livro> livros = new ArrayList<>();
            livros.add(livro);
            livros.add(livro1);
            livros.add(livro2);
            

            if (escolha == 1){
                System.out.println("\nLivro cadastrado!");
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Editora: " + livro.getEditora());
                System.out.println("Ano: " + livro.getAno());

                System.out.println("\nLivro 1 cadastrado!");
                System.out.println("Título: " + livro1.getTitulo());
                System.out.println("Autor: " + livro1.getAutor());
                System.out.println("Editora: " + livro1.getEditora());
                System.out.println("Ano: " + livro1.getAno());

                System.out.println("\nLivro 2 cadastrado!");
                System.out.println("Título: " + livro2.getTitulo());
                System.out.println("Autor: " + livro2.getAutor());
                System.out.println("Editora: " + livro2.getEditora());
                System.out.println("Ano: " + livro2.getAno());
            } else if (escolha == 2){
                System.out.println(livros);
            } else if (escolha == 3){

            }
            }
        }
}
}