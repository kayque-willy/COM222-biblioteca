package biblioteca.publicacao;

public class Publicacao {

    /*---------- Atributos ----------*/
    private int id;
    private int isbn;
    private String titulo;
    private String autor;
    private String editora;
    private int ano;
    private int funcionario_codigo;

    /*---------- Métodos ----------*/
    public void publicacao() {

    }

    /*---------- Get e Set ----------*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getFuncionario_codigo() {
        return funcionario_codigo;
    }

    public void setFuncionario_codigo(int funcionario_codigo) {
        this.funcionario_codigo = funcionario_codigo;
    }

}
