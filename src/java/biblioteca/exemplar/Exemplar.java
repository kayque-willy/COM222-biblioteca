package biblioteca.exemplar;

public class Exemplar {

    /*---------- Atributos ----------*/
    private int id;
    private int numero;
    private String publicacao_isbn;
    private Double preco;

    /*---------- Métodos ----------*/
    public void exemplar() {

    }

    /*---------- Get e Set ----------*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPublicacao_isbn() {
        return publicacao_isbn;
    }

    public void setPublicacao_isbn(String publicacao_isbn) {
        this.publicacao_isbn = publicacao_isbn;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

}
