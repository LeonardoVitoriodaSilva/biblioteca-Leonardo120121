package dominio;

import java.time.LocalDate;

public class Emprestimo {
    private LocalDate data_emprestimo;
    private LocalDate data_devolucao;

    public LocalDate get_data_emprestimo(){
        return data_emprestimo;
    }
    public LocalDate get_data_devolucao(){
        return data_devolucao;
    }
    public void set_data_devolucao(LocalDate data_devolucao){
        this.data_devolucao = data_devolucao;
    }
}

