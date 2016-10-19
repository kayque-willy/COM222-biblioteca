package biblioteca.emprestimo;

import java.sql.Date;

public class Emprestimo {

    /*---------- Atributos ----------*/
    private int id;
    private int exemplar_numero;
    private int associado_codigo;
    private int publicacao_ISBN;
    private Date data_emprestimo;
    private Date data_devolucao;
    private String status;
    private Date data_maxima;
    private int dias_atraso;
    private String data_devolucao_view;

    /*---------- Métodos ----------*/
    public void emprestimo() {

    }

    /*---------- Get e Set ----------*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExemplar_numero() {
        return exemplar_numero;
    }

    public void setExemplar_numero(int exemplar_numero) {
        this.exemplar_numero = exemplar_numero;
    }

    public int getAssociado_codigo() {
        return associado_codigo;
    }

    public void setAssociado_codigo(int associado_codigo) {
        this.associado_codigo = associado_codigo;
    }

    public Date getData_emprestimo() {
        return data_emprestimo;
    }

    public void setData_emprestimo(Date data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public Date getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(Date data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPublicacao_ISBN() {
        return publicacao_ISBN;
    }

    public void setPublicacao_ISBN(int publicacao_ISBN) {
        this.publicacao_ISBN = publicacao_ISBN;
    }

    public Date getData_maxima() {
        return data_maxima;
    }

    public void setData_maxima(Date data_maxima) {
        this.data_maxima = data_maxima;
    }

    public int getDias_atraso() {
        return dias_atraso;
    }

    public void setDias_atraso(int dias_atraso) {
        this.dias_atraso = dias_atraso;
    }

    public String getData_devolucao_view() {
        return data_devolucao_view;
    }

    public void setData_devolucao_view(String data_devolucao_view) {
        this.data_devolucao_view = data_devolucao_view;
    }

}
