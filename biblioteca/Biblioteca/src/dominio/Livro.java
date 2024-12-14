package dominio;



public class Livro {
    private String autor;
    private String titulo;
    private String editora;
    private int ano;
    private Boolean emprestado = false;

    public String getAutor(){
        return autor;
    }
    public void setAutor(String autor){
        this.autor = autor;
    }
    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    public String getEditora(){
        return editora;
    }
    public void setEditora(String editora){
        this.editora = editora;
    }
    public int getAno(){
        return ano;
    }
    public void setAno(int ano){
        this.ano = ano;
    }
    public Boolean getEmprestado(){
        return emprestado;
    }
    public void setEmprestado(Boolean emprestado){
        this.emprestado = false;
    }
    @Override
    public String toString() {
        return "\nLivro" +
                "\n{Titulo: " + titulo+
                "\nAutor: " + autor +
                "\nEditora: " + editora +
                "\nAno: " + ano +
                "\nEmprestado: " + emprestado +
                '}';
    }
}
